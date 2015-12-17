package net.logicsquad.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day5 {
	private static final String INPUT_FILENAME = "etc/day5.input";
	private static final String[] BAD_STRINGS = { "ab", "cd", "pq", "xy" };

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME),
				StandardCharsets.UTF_8);
		int count = 0;
		for (String line : lines) {
			if (isNice(line)) {
				count++;
			}
		}
		System.out.println("Day5.main: count = " + count);
	}

	private static int vowelCount(String s) {
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				result++;
			}
		}
		return result;
	}

	private static boolean containsDoubleLetter(String s) {
		boolean result = false;
		for (int i = 0; i < s.length() - 1; i++) {
			char c1 = s.charAt(i);
			char c2 = s.charAt(i + 1);
			if (c1 == c2) {
				result = true;
				break;
			}
		}
		return result;
	}

	private static boolean containsBadString(String s) {
		boolean result = false;
		for (String bad : BAD_STRINGS) {
			if (s.contains(bad)) {
				result = true;
				break;
			}
		}
		return result;
	}

	private static boolean isNice(String s) {
		return hasRepeatedLetterWithSingleGap(s) && hasNonOverlappingRepeatedPair(s);
	}

	private static boolean hasNonOverlappingRepeatedPair(String s) {
		for (int i = 0; i < s.length() - 1; i++) {
			char c1 = s.charAt(i);
			char c2 = s.charAt(i + 1);
			String pair = new StringBuilder().append(c1).append(c2).toString();
			if (s.substring(i + 2).contains(pair)) {
				return true;
			}
		}
		return false;
	}

	private static boolean hasRepeatedLetterWithSingleGap(String s) {
		for (int i = 0; i < s.length() - 2; i++) {
			char c1 = s.charAt(i);
			char c2 = s.charAt(i + 2);
			if (c1 == c2) {
				return true;
			}
		}
		return false;
	}
}
