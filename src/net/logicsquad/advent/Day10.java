package net.logicsquad.advent;

public class Day10 {
	public static void main(String[] args) {
		String input = "1321131112";
		for (int i = 0; i < 40; i++) {
			input = lookAndSay(input);
		}
		System.out.println("Day10.main: input.length() = " + input.length());
		return;
	}

	public static String lookAndSay(String input) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			int count = 1;
			while (i + count < input.length() && input.charAt(i + count) == c) {
				count++;
			}
			result.append(count);
			result.append(c);
			i += count - 1;
		}
		return result.toString();
	}
}
