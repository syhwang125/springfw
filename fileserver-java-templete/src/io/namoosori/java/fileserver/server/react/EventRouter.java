package io.namoosori.java.fileserver.server.react;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import io.namoosori.java.fileserver.context.FileCommand;
import io.namoosori.java.fileserver.server.handler.FileDeleteHandler;
import io.namoosori.java.fileserver.server.handler.FileFindHandler;
import io.namoosori.java.fileserver.server.handler.FileHandler;
import io.namoosori.java.fileserver.server.handler.FileListHandler;
import io.namoosori.java.fileserver.server.handler.FileStoreHandler;
import io.namoosori.java.fileserver.util.ByteUtil;
import io.namoosori.java.fileserver.util.ReactFailException;
import io.namoosori.java.fileserver.util.RequestMessage;
import io.namoosori.java.fileserver.util.ResponseMessage;

public class EventRouter {
	//
	private static final String DEFAULT_CHAR_SET = "UTF-8";
	public static final int HEADER_LENGTH = 4;
	private static int MAX_READ_WRITE_LENGTH = (1024 * 1024 * 10);

	private InputStream inputStream;
	private OutputStream outputStream;

	private Socket socket;

	public EventRouter(Socket socket) {
		//
		this.socket = socket;
		try {
			this.inputStream = socket.getInputStream();
			this.outputStream = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void route() {
		//
		String json = readMessage();
		RequestMessage request = RequestMessage.fromJson(json);

		String serviceName = request.getServiceName();
		FileCommand command = FileCommand.valueOf(serviceName);

		FileHandler fileHandler = null;

		switch(command) {
		case Store:
			fileHandler = new FileStoreHandler();
			break;
		case Delete:
			fileHandler = new FileDeleteHandler();
			break;
		case Find:
			fileHandler = new FileFindHandler();
			break;
		case ListFiles:
			fileHandler = new FileListHandler();
			break;
		}

		ResponseMessage response = fileHandler.handle(request);
		try {
			writeMessage(response.toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (!this.socket.isClosed()) {
				this.socket.close();
			}
		} catch (IOException e) {
			throw new ReactFailException("Fail to close the socket --> " + e.getMessage());
		}
	}

	private String readMessage() {
		//
		String resultMessage = null;

		// TODO
		//  1. read message from server
		//  2. save message to variable 'resultMessage'

		return resultMessage;
	}

	private void writeMessage(String message) throws IOException {
		//
		write(ByteUtil.toBytes(message.length()));
		write(message.getBytes());
	}

	public byte[] read(int targetLen) throws IOException {

		if (targetLen > MAX_READ_WRITE_LENGTH) {
			throw new ReactFailException("Can't read more than 10MB -> " + targetLen);
		}

		int readCount = 0;
		int retryCount = 0;
		int allReadCount = 0;
		byte[] readBuffer = new byte[targetLen];

		while (allReadCount < targetLen) {
			readCount = inputStream.read(readBuffer, allReadCount, targetLen - allReadCount);
			if (readCount > 0) {
				allReadCount += readCount;
			} else if (readCount == -1) {
				throw new ReactFailException("Read EOF.") ;
			} else if (readCount == 0 && ++retryCount == 20) {
				throw new ReactFailException("Retry more than 20 times.") ;
			}
		}
		return readBuffer;
	}

	public void write(byte[] buffer) throws IOException {
		//
		if (outputStream == null || buffer == null) {
			throw new ReactFailException("buffer is null.");
		}

		if (buffer.length > MAX_READ_WRITE_LENGTH) {
			throw new ReactFailException("Can't write more than 10MB -> " + buffer.length);
		}

		outputStream.write(buffer);
	}
}
