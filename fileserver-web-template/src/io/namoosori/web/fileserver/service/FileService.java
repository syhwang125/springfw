package io.namoosori.web.fileserver.service;

import java.io.File;
import java.util.List;

public interface FileService {
	//
	String upload(File file);
	String delete(String fileName);
	byte[] download(String fileName);
	List<String> listFiles();
	List<String> listFilesNameLike(String nameLike);
}
