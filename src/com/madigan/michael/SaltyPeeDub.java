package com.madigan.michael;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.*;

public class SaltyPeeDub {
	
	private static String salt = new String("saltydog");
	
	 private static String getHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		 
	       MessageDigest digest = MessageDigest.getInstance("SHA-1");
	       digest.reset();
	       digest.update(salt.getBytes("UTF-8"));
	       return Hex.encodeHexString(digest.digest(password.getBytes("UTF-8")));
		
	   }
	 

	public static String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return getHash(password);
	}

	
	public static boolean ComparePasswords(String password, String hashedPassword){
		boolean success = false;
		
		try
		{
			String newHashedPassword = getHash(password);
			
			success = (newHashedPassword == hashedPassword);
		}
		catch(Exception ignore)	{
		}
		
		return success;
	}
	
}
