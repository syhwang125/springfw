package io.namoosori.web.fileserver.service;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.namoosori.web.fileserver.store.FileStore;
import io.namoosori.web.fileserver.util.FileUtil;
import lombok.RequiredArgsConstructor;

@Service
public class FileServiceLogic implements FileService {
    //
	// TODO Dependency Injection
	//  1. FileStore
	//  2. FileUtil
	
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
		
		return null;
	}
	@Override
	public List<String> listFiles() {

		// TODO Implements method
		
		return null;
	}
	@Override
	public List<String> listFilesNameLike(String nameLike) {

		// TODO Implements method
		
		return null;
	}
    
}