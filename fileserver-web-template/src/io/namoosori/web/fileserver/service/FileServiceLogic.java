package io.namoosori.web.fileserver.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.namoosori.web.fileserver.context.FileContext;
import io.namoosori.web.fileserver.store.FileStore;
import io.namoosori.web.fileserver.util.FileUtil;

@Service
public class FileServiceLogic implements FileService {
	//
	// TODO Dependency Injection
	// 1. FileStore
	// 2. FileUtil
	@Autowired
	private FileStore fileStore;
	
	@Autowired
	private FileUtil fileUtil;

	@Override
	public String upload(File file) {

		// TODO Implements method
		try {
			byte[] contents = fileUtil.read(file);
			fileStore.writeFile(file.getName(), contents);
		} catch (FileAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return file.getName();
	}

	@Override
	public String delete(String fileName) {

		// TODO Implements method
		fileStore.deleteFile(fileName);
		return fileName;
	}

	@Override
	public byte[] download(String fileName) {

		// TODO Implements method		
		return fileStore.readFile(fileName);
	}

	@Override
	public List<String> listFiles() {

		// TODO Implements method
		List<String> fileList = fileStore.listFiles();
		return fileList;
	}

	@Override
	public List<String> listFilesNameLike(String nameLike) {

		// TODO Implements method
		List<String> likeFileList = new ArrayList<>();
		List<String> fileList = fileStore.listFiles();
		for (String filename : fileList) {
			if (filename.contains(nameLike)) {
				likeFileList.add(filename);
			}
		}
		return likeFileList;
	}

}