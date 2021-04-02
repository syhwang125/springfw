package io.namoosori.java.fileserver.server.handler;


import io.namoosori.java.fileserver.server.repo.FileStore;
import io.namoosori.java.fileserver.util.RequestMessage;
import io.namoosori.java.fileserver.util.ResponseMessage;

public class FileDeleteHandler implements FileHandler {
	//
	public FileDeleteHandler() {
		//

	}

	@Override
	public ResponseMessage handle(RequestMessage request) {
		//
		// TODO Implement method
		String fileName = request.getValue();
		getFileStore().deleteFile(fileName);
		ResponseMessage response = new ResponseMessage(request.getServiceName(), fileName);
		return response;
	}

	public FileStore getFileStore() {
		//
		return FileStore.newInstance();
	}
}
