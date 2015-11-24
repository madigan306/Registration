package com.madigan.michael;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Hex;

public class HashWithSalt {
	
	private static String salt = new String("saltydog");
	
	 public static String getHash(String source) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		 
	       MessageDigest digest = MessageDigest.getInstance("SHA-1");
	       digest.reset();
	       digest.update(salt.getBytes("UTF-8"));
	       return Hex.encodeHexString(digest.digest(source.getBytes("UTF-8")));
	   }
	
}
