package com.waffa.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;

@Component
public class PasswordEncryptDecrypt {
	/*
	 * Autowiring BCryptPasswordEncoder class for encoding
	 */
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/*
	 * Getting security key value from property file
	 */
	@Value("${security.key}")
	private String key;

	/*
	 * Defining algorithm to decrypt password
	 */
	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

	/*
	 * Method to decrypt network password
	 */
	public String decrypt(String encryptedData) throws Exception {
		String ivspec = key;
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivspec.getBytes("UTF-8")));
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		return new String(decValue);

	}

	/*
	 * Method to generate key based on salt
	 */
	private Key generateKey() throws IllegalArgumentException {
		byte[] salt = key.getBytes(StandardCharsets.UTF_8);
		Key key = new SecretKeySpec(salt, "AES");
		return key;
	}

	/*
	 * Method to encrypt password
	 */
	public String encryptPassword(String decryptedPassword) {

		String encryptedPassword = passwordEncoder.encode(decryptedPassword);
		return encryptedPassword;
	}

}
