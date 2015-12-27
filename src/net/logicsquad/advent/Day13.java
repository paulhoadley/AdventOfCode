package net.logicsquad.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.logicsquad.advent.utils.Permutation;

public class Day13 {
	private static final String INPUT_FILENAME = "etc/day13.input";

	public static void main(String[] args) throws IOException {
		Map<String, Map<String, Integer>> happyMap = new HashMap<String, Map<String,Integer>>();
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME), StandardCharsets.UTF_8);
		for (String line : lines) {
			parse(line, happyMap);
		}
		int max = 0;
		Permutation<String> perm = new Permutation<String>(happyMap.keySet());
		for (List<String> list : perm) {
			int happy = 0;
			for (int i = 0; i < list.size(); i++) {
				int next = (i + 1) % list.size();
				happy += happyMap.get(list.get(next)).get(list.get(i));
				happy += happyMap.get(list.get(i)).get(list.get(next));
			}
			if (happy > max) {
				max = happy;
			}
		}
		System.out.println("Day13.main: max = " + max);
	}

	private static void parse(String line, Map<String, Map<String, Integer>> map) {
		Pattern p = Pattern.compile("(\\w*) would (gain|lose) (\\d*) happiness units by sitting next to (\\w*).");
		Matcher m = p.matcher(line);
		m.find();
		Map<String, Integer> inner = map.get(m.group(1));
		if (inner == null) {
			inner = new HashMap<String, Integer>();
			map.put(m.group(1), inner);
		}
		int value = Integer.parseInt(m.group(3));
		if ("lose".equals(m.group(2))) {
			value = -value;
		}
		inner.put(m.group(4), value);
		return;
	}
}
