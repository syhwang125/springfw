package io.namoosori.web.fileserver.store;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
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
		
		return null;
	}

	public void deleteFile(String fileName) {
		//
		// TODO Implements method
		
	}

	public void writeFile(String fileName, byte[] contents) throws FileAlreadyExistsException {
		//
		//
		// TODO Implements method
		
	}

	public List<String> listFiles() {
		//
		//
		// TODO Implements method
		
		return null;
	}

}
