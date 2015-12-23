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
		int encoded = 0;
		for (String line : lines) {
			literal += line.length();
			encoded += 2;
			for (int i = 0; i < line.length();) {
				char c = line.charAt(i);
				switch (c) {
				case '"':
					i++;
					encoded += 2;
					break;
				case '\\':
					char d = line.charAt(i + 1);
					encoded += 2;
					switch (d) {
					case '\\':
					case '"':
						encoded += 2;
						memory += 1;
						i += 2;
						break;
					case 'x':
						encoded += 3;
						memory += 1;
						i += 4;
						break;
					default:
						break;
					}
					break;
				default:
					encoded += 1;
					memory += 1;
					i += 1;
					break;
				}
			}
		}
		System.out.println("Day8.main: literal - memory = " + (literal - memory));
		System.out.println("Day8.main: encoded - literal = " + (encoded - literal));
	}
}
