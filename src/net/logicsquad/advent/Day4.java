package net.logicsquad.advent;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Day4 {
	private static final String KEY = "yzbqklnj";
	
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		int counter = 1;
		while (true) {
			String candidate = KEY + counter;
			byte[] bytes = candidate.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(bytes);
			String result = DatatypeConverter.printHexBinary(digest);
			if (result.startsWith("00000")) {
				break;
			}
			counter++;
		}
		System.out.println("Day4.main: counter = " + counter);
	}
}
