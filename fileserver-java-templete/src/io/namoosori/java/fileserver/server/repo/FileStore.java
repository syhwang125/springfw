package io.namoosori.java.fileserver.server.repo;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.namoosori.java.fileserver.context.FileContext;

public class FileStore {
	//
	private FileStore() {
		//
	}

	public static FileStore newInstance() {
		//
		return new FileStore();
	}

	public char[] readFile(String fileName) {
		//
		File file = findFile(
				FileContext.SERVER_REPOSITORY_FOLDER,
				fileName);

		if (!file.exists()) {
			return null;
		}

		BufferedReader reader = null;
		CharArrayWriter contents = new CharArrayWriter((int)file.length());

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;

			while (true) {
				if((line = reader.readLine()) == null) {
					break;
				}
				contents.write(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return contents.toCharArray();
	}

	public void deleteFile(String fileName) {
		//
		File resourceFile = null;

		try {
			String fileSeparator = System.getProperty("file.separator");
			String pathName = getPathName(FileContext.SERVER_REPOSITORY_FOLDER);
			String fullFileName = pathName + fileSeparator + fileName;

			Path path = Paths.get(pathName);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}

			resourceFile = new File(fullFileName);
			if (resourceFile.exists()) {
				//
				resourceFile.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFile(String fileName, char[] contents) throws FileAlreadyExistsException {
		//
		File file = findFile(
				FileContext.SERVER_REPOSITORY_FOLDER,
				fileName);

		if (file.exists()) {
			deleteFile(fileName);
			throw new FileAlreadyExistsException(fileName);
		}

		try(FileWriter fileWriter = new FileWriter(file)) {
			String fileSeparator = System.getProperty("file.separator");
			String pathName = getPathName(FileContext.SERVER_REPOSITORY_FOLDER);
			String fullFileName = pathName + fileSeparator + fileName;

			Path path = Paths.get(pathName);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}

			file = new File(fullFileName);
			file.createNewFile();
			fileWriter.write(contents);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> listFiles() {
		//
		List<String> fileList = new ArrayList<>();

		try {
			String pathName = getPathName(FileContext.SERVER_REPOSITORY_FOLDER);
			Path path = Paths.get(pathName);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			} else {
				File folder = new File(pathName);
				String[] fileNames = folder.list();
				fileList = Arrays.asList(fileNames);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileList;
	}

	private File findFile(String folderName, String fileName) {
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

		return  builder.toString();
	}

	public static void sampleFiles() {
		FileStore store = newInstance();
		for(int i=0; i<10; i++) {
			String fileName = String.format("sample_%d.txt", i);
			char[] content = String.format("hello world %d!", i).toCharArray();
			try {
				store.writeFile(fileName, content);
			}catch (FileAlreadyExistsException e){
				System.out.println("already init...");
				break;
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
}
