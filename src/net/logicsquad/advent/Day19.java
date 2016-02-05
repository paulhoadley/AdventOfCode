package net.logicsquad.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
		int count = 0;
		while (!"e".equals(start)) {
			for (Replacement r : repls) {
				int last = 0;
				while (last != -1) {
					last = start.indexOf(r.output, last);
					if (last != -1) {
						count++;
						last += r.output.length();
					}
				}
				start = start.replaceAll(r.output, r.input);
			}
		}
		System.out.println("Day19.main: count = " + count);
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
