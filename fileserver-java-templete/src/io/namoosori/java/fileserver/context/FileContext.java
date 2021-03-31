package io.namoosori.java.fileserver.context;

import java.io.File;

public class FileContext {
	//
	public static final String SERVER_REPOSITORY_FOLDER = "FileRepository";
	public static final String CLIENT_REPOSITORY_FOLDER = "ClientRepository";
	public static final String CLIENT_DOWNLOAD_FOLDER = String.join(File.separator, CLIENT_REPOSITORY_FOLDER, "download");
}
