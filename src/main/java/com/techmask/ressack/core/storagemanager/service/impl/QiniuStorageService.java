package com.techmask.ressack.core.storagemanager.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.techmask.ressack.core.log.LogUtils;
import com.techmask.ressack.core.storagemanager.service.StorageService;

@Configuration
@Service
public class QiniuStorageService implements StorageService {

	private static final String BUCKET_NAME = "jijiangshe-public";

	@Value("${storage.qiniu.accessKey}")
	private String accessKey;
	@Value("${storage.qiniu.secretKey}")
	private String secretKey;

	UploadManager uploadManager = new UploadManager();
	private Auth auth;

	
	
	public String getUpToken() {
		if (auth == null) {
			auth = Auth.create(accessKey, secretKey);
		}
		return auth.uploadToken(BUCKET_NAME);
	}
	
	public String getUpToken(String key) {
		if (auth == null) {
			auth = Auth.create(accessKey, secretKey);
		}
		return auth.uploadToken(BUCKET_NAME,key);
	}

	public void upload(byte[] data, String fileName) throws IOException {
		try {
			Response res = uploadManager.put(data, fileName, getUpToken());
		} catch (QiniuException e) {
			Response r = e.response;
			LogUtils.error(r.toString());

		}
	}
	
	public void upload(String filePath, String fileName) throws IOException {
		upload(filePath,fileName,false);
	}

	public void upload(String filePath, String fileName, boolean overide) throws IOException {
		try {
			
			String token = overide?getUpToken(fileName):getUpToken();
			Response res = uploadManager.put(filePath, fileName, token);
		} catch (QiniuException e) {

			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				LogUtils.error(r.toString());
			}
			

		}
	}

}
