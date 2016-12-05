package com.wangying.rpcdemo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Provider {

	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(3000);
		while (true) {
			Socket socket = server.accept();
			System.out.println("服务器连接");
			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			Request request = (Request) is.readObject();
			String methodName=request.methodName;
			String className=request.className;

			Class ownerClass = Class.forName(className);  
			System.out.println(className);
			Class[] argsClass = new Class[request.list.size()];
			Object[] argObject=new Object[request.list.size()];
			for (int i = 0, j = request.list.size(); i < j; i++) {
				argsClass[i] = request.list.get(i).getClass();
				argObject[i]=request.list.get(i);
				System.out.println(argsClass[i].getName());
			}

			Method method = ownerClass.getMethod(methodName, argsClass);
			System.out.println(args.length);
			
			Object obj =method.invoke(null,argObject);
			
			Response response=new Response();
			response.result=obj;
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			os.writeObject(response);
			os.flush();
		}
	}

	public static int add(Integer a,Integer b,String c) {
		int t=a+b;
		return t;
		
	}
}
