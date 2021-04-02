package io.namoosori.web.fileserver.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.namoosori.web.fileserver.store.FileStore;
import io.namoosori.web.fileserver.util.FileUtil;

@Service
public class FileServiceLogic implements FileService {
    //
	// TODO Dependency Injection
	//  1. FileStore
	//  2. FileUtil
	@Autowired
	private FileStore fileStore;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Override
	public String upload(File file) {
		
		// TODO Implements method
		
		return null;
	}
	@Override
	public String delete(String fileName) {

		// TODO Implements method
		
		return null;
	}
	@Override
	public byte[] download(String fileName) {
		
		// TODO Implements method
//		byte[] contents ;
//		fileStore.writeFile(fileName, contents );
		
		return null;
	}
	@Override
	public List<String> listFiles() {

		// TODO Implements method
		return fileStore.listFiles();
	}
	@Override
	public List<String> listFilesNameLike(String nameLike) {

		// TODO Implements method
		List<String> likeFileList = new ArrayList<>();
		
		List<String> fileList = fileStore.listFiles();
		for(int i =0; i<fileList.size(); i++) {
			if(fileList.)
		}
		
		return likeFileList;
	}
    
}