package io.namoosori.java.fileserver.server.main;

import io.namoosori.java.fileserver.server.react.FileServerReactor;
import io.namoosori.java.fileserver.server.repo.FileStore;

public class FileServer {
	//
	public static void main(String[] args) {
		//
		FileStore.sampleFiles();
		startServer();
	}

	private static void startServer() {
		//
		FileServerReactor reactor = new FileServerReactor();
		reactor.start();
		System.out.println("FileServer is started... ");
	}
}
