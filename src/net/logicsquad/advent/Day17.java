package net.logicsquad.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.logicsquad.advent.utils.Combination;

public class Day17 {
	private static final String INPUT_FILENAME = "etc/day17.input";
	private static final int TOTAL = 150;

	public static void main(String[] args) throws IOException {
		List<Integer> ints = new ArrayList<Integer>();
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME),
				StandardCharsets.UTF_8);
		for (String line : lines) {
			ints.add(Integer.parseInt(line));
		}
		int result = 0;
		for (int i = 1; i <= ints.size(); i++) {
			Combination<Integer> comb = new Combination<Integer>(ints, i);
			for (List<Integer> candidate : comb) {
				int total = 0;
				for (Integer j : candidate) {
					total += j;
				}
				if (total == TOTAL) {
					result++;
				}
			}
		}
		System.out.println("Day17.main: result = " + result);
		return;
	}
}
