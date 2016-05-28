package com.techmask.ressack.core.storagemanager.service;

import java.io.IOException;

public interface StorageService {
	public void upload(byte[] data,String fileName) throws IOException;
	public void upload(String filePath,String fileName) throws IOException;
	
}
