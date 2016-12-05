package com.wangying.rpcdemo;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Request implements Serializable {

	String className="";
	String Info="";
	String methodName="";
	List<Object> list =new ArrayList();
	public void set(String className,String methodName, Object...args) {
		// TODO Auto-generated method stub
		this.methodName=methodName;
		this.className=className;
//		Object[] argsObject=new Object[args.length];
		for (int i = 0, j = args.length; i < j; i++) {  
//	         argsObject[i] = args[i];  
	         list.add(args[i]);
	     }
		
	}
	
	

}
