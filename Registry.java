package com.wangying.rpcdemo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Registry {

	public static HashMap<String,Ip> Map=new HashMap<String,Ip>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String m=Provider.class.getName()+"add"+Integer.class.getName()+Integer.class.getName()+String.class.getName();
		System.out.println(m);
		Ip mm=new Ip();
		mm.ip="127.0.0.1";
		mm.port=3000;
		Map.put(m, mm);
		ServerSocket CustomerServer = new ServerSocket(6666);
		ServerSocket ProviderServer = new ServerSocket(8888);
		Thread CustomerThread = new Thread(new CustomerRunnable(CustomerServer));
		Thread ProviderThread = new Thread(new ProviderRunnable(ProviderServer));
		CustomerThread.start();
		ProviderThread.start();

	}



}
