package io.namoosori.java.fileserver.client.transfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import io.namoosori.java.fileserver.util.*;

public class SocketDispatcher {
	//
	private static final int TIME_OUT_IN_SECONDS = 3;
	private static final String DEFAULT_CHAR_SET = "UTF-8";
	public static final int HEADER_LENGTH = 4;
	private static int MAX_READ_WRITE_LENGTH = (1024 * 1024 * 10);

	private InputStream inputStream;
	private OutputStream outputStream;

	private Socket socket;

	private SocketDispatcher(String serverIp, int listeningPort) {
		//
		this.socket = prepareSocket(serverIp, listeningPort);
		try {
			this.inputStream = socket.getInputStream();
			this.outputStream = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SocketDispatcher getInstance(String ip, int port) {
		return (new SocketDispatcher(ip, port));
	}

	public void close() {
		//
		try {
			if (!this.socket.isClosed()) {
				this.socket.close();
			}
		} catch (IOException e) {
			throw new ReactFailException("Fail to close the socket --> " + e.getMessage());
		}
	}

	public ResponseMessage dispatchReturn(RequestMessage message) throws IOException {
		//
		write(ByteUtil.toBytes(message.toJson().length()));
		write(message.toJson().getBytes());

		System.out.println("SocketDispatcher.dispatchReturn() ==> " + message);
		String json = "";
		// TODO
		//  1. read message from server
		//  2. save message to variable 'json'
		int msgLength = ByteUtil.toInt(read(HEADER_LENGTH));
		byte[] response = read(msgLength);
		json = new String(response, DEFAULT_CHAR_SET); 
		return ResponseMessage.fromJson(json);

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

	private Socket prepareSocket(String serverIp, int listeningPort) {
		//
		Socket socket = null;
		try {
			socket = new Socket();
			socket.setSoLinger(true, 0);
			socket.setReuseAddress(true);
			socket.connect(new InetSocketAddress(serverIp, listeningPort), TIME_OUT_IN_SECONDS*1000);
		} catch (UnknownHostException e) {
			throw new DispatchFailException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new DispatchFailException(e.getMessage());
		}

		return socket;
	}
}
