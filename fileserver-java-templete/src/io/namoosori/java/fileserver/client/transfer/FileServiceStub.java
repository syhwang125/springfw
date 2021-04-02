package io.namoosori.java.fileserver.client.transfer;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.namoosori.java.fileserver.context.FileCommand;
import io.namoosori.java.fileserver.context.FileContext;
import io.namoosori.java.fileserver.context.FileService;
import io.namoosori.java.fileserver.util.RequestMessage;
import io.namoosori.java.fileserver.util.ResponseMessage;

public class FileServiceStub implements FileService {
	//
	public FileServiceStub() {
		//
	}

	@Override
	public String upload(File file) {
		//
		SocketDispatcher dispatcher = getDispatcher();

		char[] contents = read(file);
		RequestMessage requestMessage = new RequestMessage(FileCommand.Store.name(), String.valueOf(contents));
		requestMessage.setRemark(file.getName());

		ResponseMessage response = null;
		try {
			response = dispatcher.dispatchReturn(requestMessage);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		dispatcher.close();

		return response.getValue();
	}

	@Override
	public String delete(String fileName) {
		//
		// TODO Implement method (Use SocketDispatcher)
		SocketDispatcher dispatcher = getDispatcher();

		RequestMessage requestMessage = new RequestMessage(FileCommand.Delete.name(), fileName);

		ResponseMessage response = null;
		try {
			response = dispatcher.dispatchReturn(requestMessage);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		dispatcher.close();

		return response.getValue();
	}

	@Override
	public String download(String fileName) {
		//
		// TODO Impelement method (Use SocketDispatcher)
		SocketDispatcher dispatcher = getDispatcher();

		RequestMessage requestMessage = new RequestMessage(FileCommand.Find.name(), fileName);

		ResponseMessage response = null;
		try {
			response = dispatcher.dispatchReturn(requestMessage);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		dispatcher.close();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fileData = response.getValue();
		File file = createFile(FileContext.CLIENT_DOWNLOAD_FOLDER, fileName);
		write(file, fileData.toCharArray());
		
		return fileName;
	}

	@Override
	public List<String> listFiles() {
		//
		// TODO Implement method (Use SocketDispatcher)

		SocketDispatcher dispatcher = getDispatcher();
		String delimiter = "#";
		RequestMessage requestMessage = new RequestMessage(FileCommand.ListFiles.name(), delimiter);

		ResponseMessage response = null;
		try {
			response = dispatcher.dispatchReturn(requestMessage);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		dispatcher.close();
		String[] fileList = response.getValue().split(delimiter);

		return Arrays.asList(fileList);
	}

	@Override
	public List<String> downloadAll(boolean multiThreadMode) {
		List<String> fileList = listFiles();

		System.out.format("\n## multiThreadMode:%s, fileCount:%d Started... \n", multiThreadMode, fileList.size());
		long begin = System.currentTimeMillis();
		long end = 0;
		Thread task = null;
		if (multiThreadMode) {
			// TODO
			// 1. download files on multi thread
			// 2. set end time after all threads completed (hint: do not set end time before
			// thread completed)
			
			System.out.println(Runtime.getRuntime().availableProcessors());
			
			fileList.stream()
			        .parallel()
			        .forEach(name -> download(name));
			
			end = System.currentTimeMillis();
		} else {
			for (String fileName : fileList) {
				download(fileName);
			}
			end = System.currentTimeMillis();
		}

		System.out.format(" ==> 처리 시간: %f \n", (end - begin) / 1000.0);

		return fileList;
	}

	private SocketDispatcher getDispatcher() {
		//
		return SocketDispatcher.getInstance("127.0.0.1", 3333);
	}

	private char[] read(File file) {
		//
		BufferedReader reader = null;
		CharArrayWriter contents = new CharArrayWriter((int) file.length());

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;

			while (true) {
				if ((line = reader.readLine()) == null) {
					break;
				}
				contents.write(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return contents.toCharArray();
	}

	private File createFile(String folderName, String fileName) {
		//
		File resourceFile = null;

		try {
			String fileSeparator = System.getProperty("file.separator");
			String pathName = getPathName(folderName);
			String fullFileName = pathName + fileSeparator + fileName;

			Path path = Paths.get(pathName);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}

			resourceFile = new File(fullFileName);
			resourceFile.createNewFile();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return resourceFile;
	}

	private String getPathName(String folderName) throws IOException {
		//
		String cannonicalPath = (new File(".")).getCanonicalPath();
		String fileSeparator = System.getProperty("file.separator");

		StringBuilder builder = new StringBuilder();
		builder.append(cannonicalPath).append(fileSeparator);
		builder.append("files").append(fileSeparator);
		builder.append(folderName);

		return builder.toString();
	}

	public void write(File file, char[] contents) {
		//
		try (FileWriter fileWriter = new FileWriter(file)) {
			fileWriter.write(contents);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sampleFiles() {
		//
		FileServiceStub stub = new FileServiceStub();
		for (int i = 0; i < 5; i++) {
			File sample = stub.createFile(FileContext.CLIENT_REPOSITORY_FOLDER,
					String.format("client_sample_%d.txt", i));
			char[] content = String.format("hello i'm client_%d", i).toCharArray();
			stub.write(sample, content);
		}
	}
}
