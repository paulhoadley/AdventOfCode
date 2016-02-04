package net.logicsquad.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {
	private static final String INPUT_FILENAME = "etc/day19.input";

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME), StandardCharsets.UTF_8);
		List<Replacement> repls = new ArrayList<Replacement>();
		Pattern p = Pattern.compile("(\\w*) => (\\w*)");
		for (int i = 0; i < lines.size() - 2; i++) {
			Matcher m = p.matcher(lines.get(i));
			m.find();
			repls.add(new Replacement(m.group(1), m.group(2)));
		}
		String start = lines.get(lines.size() - 1);
		Set<String> results = new HashSet<String>();
		for (Replacement repl : repls) {
			int index, cursor;
			String sub = start;
			while (sub.contains(repl.input)) {
				cursor = start.length() - sub.length();
				index = sub.indexOf(repl.input) + repl.input.length();
				sub = sub.replaceFirst(repl.input, repl.output);
				String result = start.substring(0, cursor) + sub;
				results.add(result);
				sub = start.substring(cursor + index);
			}
		}
		System.out.println("Day19.main: results.size() = " + results.size());
	}

	public static class Replacement {
		public final String input;
		public final String output;
		public Replacement(String input, String output) {
			this.input = input;
			this.output = output;
			return;
		}
		@Override
		public String toString() {
			return input + " => " + output;
		}
	}
}
