package io.namoosori.java.fileserver.server.handler;


import io.namoosori.java.fileserver.server.repo.FileStore;
import io.namoosori.java.fileserver.util.RequestMessage;
import io.namoosori.java.fileserver.util.ResponseMessage;

public class FileFindHandler implements FileHandler {
	//
	public FileFindHandler() {
		//
	}

	@Override
	public ResponseMessage handle(RequestMessage request) {
		//
		// TODO Implement method
		return null;
	}

	public FileStore getFileStore() {
		//
		return FileStore.newInstance();
	}
}