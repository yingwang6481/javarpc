package com.wangying.rpcdemo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomerRunnable implements Runnable {

	
	ServerSocket CustomerServer;

	public CustomerRunnable(ServerSocket CustomerServer) {
		this.CustomerServer = CustomerServer;
	}

	@Override
	public void run() {
		ExecutorService pool=Executors.newFixedThreadPool(2);
		while (true) {
			try {
				Socket socket=CustomerServer.accept();
				ObjectInputStream is=null;
				ObjectOutputStream os=null;
				System.out.println("customer连接");
				pool.execute(new MyRunnable(socket,is,os));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void service(Socket socket,ObjectInputStream is,ObjectOutputStream os) throws IOException, ClassNotFoundException{
		
		String methodName="";
		String className="";
		String key="";
		
		   
		
		 os = new ObjectOutputStream(socket.getOutputStream());
		
		
		is = new ObjectInputStream(socket.getInputStream()); 
		Request request =(Request)is.readObject();
		
		methodName=request.methodName;
		className=request.className;

		System.out.println(className);
		key+=className;
		key+=methodName;
		Object[] args=new Object[request.list.size()];
		
		for(int i=0;i<request.list.size();i++){
			args[i]=request.list.get(i);
			key+=args[i].getClass().getName();
		}
		Ip ip=Registry.Map.get(key);//此处应加上为空的代码
		System.out.println(key);
		Socket psocket=new Socket(ip.ip,ip.port);
		ObjectOutputStream pos=new ObjectOutputStream(psocket.getOutputStream());
		pos.writeObject(request);
		pos.flush();
		ObjectInputStream   pis = new ObjectInputStream(new BufferedInputStream(psocket.getInputStream())); 
		Response response=(Response)pis.readObject();
		pis.close();
		os.writeObject(response);
		os.flush();
	}
	class MyRunnable implements Runnable{  
		Socket socket;
		ObjectInputStream is=null;
		ObjectOutputStream os=null;
		public MyRunnable(Socket socket,ObjectInputStream is,ObjectOutputStream os){
			this.socket=socket;
			this.is=is;
			this.os=os;
		}
	   
	    @Override  
	    public void run() {  
	    	try {
	    		
				service(socket,is,os);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }  
	}  
}