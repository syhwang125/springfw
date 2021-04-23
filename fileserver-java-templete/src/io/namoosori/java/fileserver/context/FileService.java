package io.namoosori.java.fileserver.context;

import java.io.File;
import java.util.List;

public interface FileService {
	//
	String upload(File file) throws Exception;
	String delete(String fileName);
	String download(String fileName);
	List<String> listFiles();
	List<String> downloadAll(boolean multiThreadMode);
}
