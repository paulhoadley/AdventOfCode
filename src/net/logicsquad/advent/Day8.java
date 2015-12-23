package net.logicsquad.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day8 {
	private static final String INPUT_FILENAME = "etc/day8.input";
	
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME), StandardCharsets.UTF_8);
		int literal = 0;
		int memory = 0;
		for (String line : lines) {
			literal += line.length();
			for (int i = 0; i < line.length();) {
				char c = line.charAt(i);
				switch (c) {
				case '"':
					i++;
					break;
				case '\\':
					char d = line.charAt(i + 1);
					switch (d) {
					case '\\':
					case '"':
						memory += 1;
						i += 2;
						break;
					case 'x':
						memory += 1;
						i += 4;
						break;
					default:
						break;
					}
					break;
				default:
					memory += 1;
					i += 1;
					break;
				}
			}
		}
		System.out.println("Day8.main: literal - memory = " + (literal - memory));
	}
}
