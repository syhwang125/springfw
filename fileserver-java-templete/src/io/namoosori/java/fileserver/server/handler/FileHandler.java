package io.namoosori.java.fileserver.server.handler;

import io.namoosori.java.fileserver.util.RequestMessage;
import io.namoosori.java.fileserver.util.ResponseMessage;

public interface FileHandler {
	//
	ResponseMessage handle(RequestMessage request);
}
