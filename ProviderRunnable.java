package com.wangying.rpcdemo;

import java.net.ServerSocket;

public class ProviderRunnable implements Runnable {
	int i = 0;
	ServerSocket ProviderServer;

	public ProviderRunnable(ServerSocket ProviderServer) {
		this.ProviderServer = ProviderServer;
	}

	@Override
	public void run() {
		while (true) {

		}
	}
}