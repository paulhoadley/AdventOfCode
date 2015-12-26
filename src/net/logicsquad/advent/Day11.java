package net.logicsquad.advent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day11 {
	private static final String INPUT = "cqjxjnds";

	public static void main(String[] args) {
		String candidate = increment(INPUT);
		while (!allowed(candidate)) {
			candidate = increment(candidate);
		}
		System.out.println("Day11.main: next = " + candidate);
	}

	private static boolean allowed(String string) {
		if (!containsStraightOfLength(string, 3)) {
			return false;
		}
		List<String> list = new ArrayList<String>();
		list.add("i");
		list.add("o");
		list.add("l");
		if (containsAny(string, list)) {
			return false;
		}
		if (distinctPairs(string).size() < 2) {
			return false;
		}
		return true;
	}

	private static String increment(String string) {
		char[] chars = string.toCharArray();
		for (int i = chars.length - 1; i >= 0; i--) {
			char c = chars[i];
			if (c == 'z') {
				c = 'a';
				chars[i] = c;
			} else {
				c++;
				chars[i] = c;
				break;
			}
		}
		return String.valueOf(chars);
	}

	private static boolean containsStraightOfLength(String string, int length) {
		for (int i = 0; i < (string.length() - length + 1); i++) {
			if (isStraight(string.substring(i, i + length))) {
				return true;
			}
		}
		return false;
	}

	private static boolean isStraight(String string) {
		for (int i = 0; i < string.length() - 1; i++) {
			char c = string.charAt(i);
			if (c != string.charAt(i + 1) - 1) {
				return false;
			}
		}
		return true;
	}

	private static boolean containsAny(String string, List<String> targets) {
		for (String target : targets) {
			if (string.contains(target)) {
				return true;
			}
		}
		return false;
	}

	private static Set<Character> distinctPairs(String string) {
		Set<Character> result = new HashSet<Character>();
		for (int i = 0; i < string.length() - 1; i++) {
			char c = string.charAt(i);
			if (c == string.charAt(i + 1)) {
				result.add(new Character(c));
			}
		}
		return result;
	}
}
