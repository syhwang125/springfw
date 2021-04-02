package io.namoosori.web.fileserver.store;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import io.namoosori.web.fileserver.context.FileContext;
import io.namoosori.web.fileserver.util.FileUtil;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FileStore {
	//
	private final FileUtil fileUtil;

	public byte[] readFile(String fileName) {
		//
		// TODO Implements method
		File file = fileUtil.findFile(FileContext.SERVER_REPOSITORY_FOLDER, fileName);
		if (!file.exists()) {
			System.out.println("File Not Found.");
			return null;
		}
		return fileUtil.read(file);
	}

	public void deleteFile(String fileName) {
		//
		// TODO Implements method
		String folderName = FileContext.SERVER_REPOSITORY_FOLDER;
		fileUtil.deleteFile(folderName, fileName);
	}

	public void writeFile(String fileName, byte[] contents) throws FileAlreadyExistsException {
		//
		//
		// TODO Implements method
		File checkfile = fileUtil.findFile(FileContext.SERVER_REPOSITORY_FOLDER, fileName);
		if(!checkfile.exists()) {
			File file = fileUtil.createFile(FileContext.SERVER_REPOSITORY_FOLDER, fileName);
			// 
			if(file != null) {
				fileUtil.write(file, contents);
			}
		} 

	}

	public List<String> listFiles() {
		//
		//
		// TODO Implements method
		List<String> fileList = fileUtil.findFileList(FileContext.SERVER_REPOSITORY_FOLDER);
		return fileList;
	}

}
