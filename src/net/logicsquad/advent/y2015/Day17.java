package net.logicsquad.advent.y2015;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.logicsquad.advent.y2015.utils.Combination;

public class Day17 {
	private static final String INPUT_FILENAME = "etc/2015/day17.input";
	private static final int TOTAL = 150;

	public static void main(String[] args) throws IOException {
		List<Integer> ints = new ArrayList<Integer>();
		Map<Integer, Integer> leaderboard = new HashMap<Integer, Integer>();
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME),
				StandardCharsets.UTF_8);
		for (String line : lines) {
			ints.add(Integer.parseInt(line));
		}
		for (int i = 1; i <= ints.size(); i++) {
			Combination<Integer> comb = new Combination<Integer>(ints, i);
			for (List<Integer> candidate : comb) {
				int total = 0;
				for (Integer j : candidate) {
					total += j;
				}
				if (total == TOTAL) {
					Integer k = leaderboard.get(i);
					if (k == null) {
						k = Integer.valueOf(0);
					}
					leaderboard.put(i, ++k);
				}
			}
		}
		List<Integer> leaders = new ArrayList<Integer>(leaderboard.keySet());
		Collections.sort(leaders);
		System.out.println("Day17.main: result = " + leaderboard.get(leaders.get(0)));
		return;
	}
}
