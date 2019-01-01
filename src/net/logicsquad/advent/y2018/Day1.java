package net.logicsquad.advent.y2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
	private static final String INPUT_FILENAME = "etc/2018/day1.input";

	public static void main(String args[]) throws IOException {
		try (Stream<String> values = Files.lines(Paths.get(INPUT_FILENAME))) {
			List<Integer> ints = values.map(s -> Integer.valueOf(s)).collect(Collectors.toList());
			Integer current = Integer.valueOf(0);
			List<Integer> freqs = new ArrayList<>();
			Integer result = null;
			while (result == null) {
				for (Integer i : ints) {
					current = current + i;
					if (freqs.contains(current)) {
						result = current;
						break;
					} else {
						freqs.add(current);
					}
				}
			}
			System.out.println("Day1.main: result = " + result);
		}
	}
}
