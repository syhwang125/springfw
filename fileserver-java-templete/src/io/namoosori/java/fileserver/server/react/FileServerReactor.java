package io.namoosori.java.fileserver.server.react;

import io.namoosori.java.fileserver.util.ReactFailException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServerReactor extends Thread {
	//
	private int servicePort;
	private ServerSocket serverSocket;

	public FileServerReactor() {
		//
		this.servicePort = 3333;
	}

	private void initServerSocket() {
		//
		try {
			serverSocket = new ServerSocket(servicePort);
		} catch (IOException e) {
			throw new ReactFailException(e.getMessage());
		}
	}

	public void run() {
		//
		this.initServerSocket();
		int count = 0;
		while(true) {
			//
			Socket clientSocket = null;

			// TODO create connection with client

			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			(new EventRouter(clientSocket)).route();
		}
	}
}
