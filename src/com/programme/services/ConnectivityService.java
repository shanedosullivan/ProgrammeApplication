package com.programme.services;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ConnectivityService  extends Service{

	private InetAddress ip;
	private static final int port = 8080;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		
		try {
			ip = InetAddress.getByName("192.168.0.104");
		    SocketAddress sockaddr = new InetSocketAddress(ip, port);
		    // Create an unbound socket
		    Socket sock = new Socket();

		    // This method will block no more than timeoutMs.
		    // If the timeout occurs, SocketTimeoutException is thrown.
		    int timeoutMs = 8000;   // 8 seconds
		    sock.connect(sockaddr, timeoutMs);
		    }
		catch(Exception e){
		}
		
		return START_NOT_STICKY;
		}
}
