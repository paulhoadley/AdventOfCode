package net.logicsquad.advent.y2018;

import java.util.List;

public class Day2 extends Day {
	private static final String INPUT_FILENAME = "etc/2018/day2.input";

	public static void main(String[] args) {
		List<String> lines = linesForFilename(INPUT_FILENAME);
		for (int i = 0; i < lines.size(); i++) {
			for (int j = i+1; j < lines.size(); j++) {
				String alpha = lines.get(i);
				String beta = lines.get(j);
				if (hammingDistance(alpha, beta) == 1) {
					System.out.println("Day2.main: result = " + common(alpha, beta));
					return;
				}
			}
		}
		return;
	}

	private static int hammingDistance(String alpha, String beta) {
		if (alpha.length() != beta.length()) {
			throw new IllegalArgumentException();
		}
		int distance = 0;
		for (int i = 0; i < alpha.length(); i++) {
			if (alpha.charAt(i) != beta.charAt(i)) {
				distance++;
			}
		}
		return distance;
	}

	private static String common(String alpha, String beta) {
		if (alpha.length() != beta.length()) {
			throw new IllegalArgumentException();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < alpha.length(); i++) {
			if (alpha.charAt(i) == beta.charAt(i)) {
				sb.append(alpha.charAt(i));
			}
		}
		return sb.toString();
	}
}
