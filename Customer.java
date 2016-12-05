package com.wangying.rpcdemo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.net.UnknownHostException;

public class Customer {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException {
		// TODO Auto-generated method stub

		String methodName = "add";
		String className ="com.wangying.rpcdemo.Provider";
		//中间代理端口号6666
		Socket client = new Socket("127.0.0.1", 6666);
		ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
		ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		Request request = new Request();
		request.set(className,methodName, 11, 2, "fasdasd");
		os.writeObject(request);
		os.flush();
		Thread.sleep(1000);
		Response response=(Response)is.readObject();
		int myresult=(int)response.result;
		System.out.println(myresult);
		client.close();
	}

}
