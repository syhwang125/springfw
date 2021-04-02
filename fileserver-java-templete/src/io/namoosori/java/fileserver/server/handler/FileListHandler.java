package io.namoosori.java.fileserver.server.handler;

import java.util.List;
import java.util.StringTokenizer;

import com.google.gson.Gson;

import io.namoosori.java.fileserver.server.repo.FileStore;
import io.namoosori.java.fileserver.util.RequestMessage;
import io.namoosori.java.fileserver.util.ResponseMessage;

public class FileListHandler implements FileHandler {
	//
	public FileListHandler() {
		//
	}

	@Override
	public ResponseMessage handle(RequestMessage request) {
		//
		// TODO Implement method
		List<String> fileList = getFileStore().listFiles();
		String delimiter = request.getValue();
		ResponseMessage response = new ResponseMessage(request.getServiceName(), String.join(delimiter, fileList));
		return response;
	}

	public FileStore getFileStore() {
		//
		return FileStore.newInstance();
	}
}
