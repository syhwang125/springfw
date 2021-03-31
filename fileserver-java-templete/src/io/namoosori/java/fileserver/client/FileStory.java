package io.namoosori.java.fileserver.client;

import io.namoosori.java.fileserver.client.transfer.FileServiceStub;
import io.namoosori.java.fileserver.client.ui.ClientConsole;

public class FileStory {
	//
	public static void main(String[] args) throws InterruptedException {
		//
		FileStory story = new FileStory();
		story.start();
	}

	public void start() throws InterruptedException {
		//
		FileServiceStub.sampleFiles();
		ClientConsole console = new ClientConsole();
		console.start();
	}
}
