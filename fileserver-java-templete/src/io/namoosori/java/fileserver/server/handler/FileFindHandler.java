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
		String fileName = request.getValue(); 
		
		char[] fileChar =  getFileStore().readFile(fileName);
		ResponseMessage response = new ResponseMessage(request.getServiceName(), String.valueOf(fileChar));
		return response;
	}

	public FileStore getFileStore() {
		//
		return FileStore.newInstance();
	}
}
