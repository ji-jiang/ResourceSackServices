package com.techmask.ressack.core.utils;

import java.util.Random;

import org.apache.commons.codec.digest.Crypt;

public class CryptUtils {
	private static final String SALT_SUFFIX = "rESQ3f";
	private static final String SALT_PREFIX = "$1$"; //MD5 Crypt
	
	private static final int PASSWORD_LENGTH = 4;
	
	public static final String crypt(String inStr, String salt){
		String cryptedStr = Crypt.crypt(inStr, SALT_PREFIX+salt+SALT_SUFFIX);
		return cryptedStr;
	}
	
	public static final String generatePassword() {
		
		int i; 
		int count = 0; 

		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };
		final int maxNum = str.length;

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < PASSWORD_LENGTH) {
			i = Math.abs(r.nextInt(maxNum));

			if (i >= 0 && i < maxNum) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();

	}
	

}
