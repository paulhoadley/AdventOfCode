package net.logicsquad.advent.y2018;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 extends Day {
	private static final String INPUT_FILENAME = "etc/2018/day2.input";

	public static void main(String[] args) {
		List<String> lines = linesForFilename(INPUT_FILENAME);
		int twos = 0;
		int threes = 0;
		for (String line : lines) {
			if (hasAtLeastOneExactlyN(line, 2)) {
				twos++;
			}
			if (hasAtLeastOneExactlyN(line, 3)) {
				threes++;
			}
		}
		System.out.println("Day2.main: twos * threes = " + (twos * threes));
		return;
	}

	private static Map<Character, Integer> counts(String input) {
		Map<Character, Integer> result = new HashMap<>();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (result.containsKey(c)) {
				result.put(c, result.get(c) + 1);
			} else {
				result.put(c, 1);
			}
		}
		return result;
	}

	private static boolean hasAtLeastOneExactlyN(String input, int n) {
		Map<Character, Integer> result = counts(input);
		for (Integer i : result.values()) {
			if (i == n) {
				return true;
			}
		}
		return false;
	}
}
