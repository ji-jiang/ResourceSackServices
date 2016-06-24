package com.techmask.ressack.core.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import com.techmask.ressack.core.log.LogUtils;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	static public String filterOffUtf8Mb4(String text) {
		try {

			if (isBlank(text)) {
				return text;
			}

			byte[] bytes = text.getBytes("utf-8");
			ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
			int i = 0;
			while (i < bytes.length) {
				short b = bytes[i];
				if (b > 0) {
					buffer.put(bytes[i++]);
					continue;
				}
				b += 256;
				if ((b ^ 0xC0) >> 4 == 0) {
					buffer.put(bytes, i, 2);
					i += 2;
				} else if ((b ^ 0xE0) >> 4 == 0) {
					buffer.put(bytes, i, 3);
					i += 3;
				} else if ((b ^ 0xF0) >> 4 == 0) {
					i += 4;
				}
			}
			buffer.flip();
			return new String(buffer.array(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			LogUtils.error("error in StringUtils.filterOffUtf8Mb4() method.", e);
			return text;
		}
	}

}
