package io.namoosori.web.fileserver.util;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

@Component
@RequiredArgsConstructor
public class FileUtil {
	//
	private final ServletContext context;

	public File createFile(String folderName, String fileName) {
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

	public File findFile(String folderName, String fileName) {
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
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resourceFile;
	}

	public void deleteFile(String folderName, String fileName) {
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
			if (resourceFile.exists()) {
				//
				resourceFile.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getPathName(String folderName) throws IOException {
		//
		String fileSeparator = System.getProperty("file.separator");

		String projectPath = context.getRealPath(fileSeparator);
		StringBuilder builder = new StringBuilder(projectPath);
		builder.append("files").append(fileSeparator);
		builder.append(folderName);

		return builder.toString();
	}

	public List<String> findFileList(String folderName) {
		//
		List<String> fileNameList = new ArrayList<>();

		try {
			String pathName = getPathName(folderName);
			Path path = Paths.get(pathName);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			} else {
				File folder = new File(pathName);
				String[] fileNames = folder.list();
				fileNameList = Arrays.asList(fileNames);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileNameList;
	}

	public boolean write(File file, byte[] contents) {
		//
		FileOutputStream fos = null;
		boolean successful = false;

		try {
			fos = new FileOutputStream(file);
			fos.write(contents);

			successful = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return successful;
	}

	public byte[] read(File file) {
		//
		BufferedReader reader = null;
		ByteArrayOutputStream contents = new ByteArrayOutputStream((int) file.length());

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;

			while (true) {
				if ((line = reader.readLine()) == null) {
					break;
				}

				contents.write(line.getBytes());
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

		return contents.toByteArray();
	}
}
