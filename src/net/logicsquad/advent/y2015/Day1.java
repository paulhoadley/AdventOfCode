package net.logicsquad.advent.y2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1 {
	private static final String INPUT_FILENAME = "etc/2015/day1.input";
	
	public static void main(String[] args) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(INPUT_FILENAME));
		String string = new String(encoded, "UTF-8");
		int floor = 0;
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (c == '(') {
				floor++;
			} else {
				floor--;
			}
			if (floor == -1) {
				System.out.println("Day1.main: position = " + (i + 1));
				break;
			}
		}
		return;
	}
}
