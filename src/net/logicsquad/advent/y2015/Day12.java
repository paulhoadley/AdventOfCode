package net.logicsquad.advent.y2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class Day12 {
	private static final String INPUT_FILENAME = "etc/2015/day12.input";

	public static void main(String[] args) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(INPUT_FILENAME));
		String string = new String(encoded, "UTF-8");
		Gson gson = new Gson();
		Object json = gson.fromJson(string, Object.class);
		System.out.println("Day12.main: sum = " + sumOfNumbers(json));
	}

	private static int sumOfNumbers(Object node) {
		int result = 0;
		if (node instanceof List<?>) {
			for (Object o : (List<?>) node) {
				result += sumOfNumbers(o);
			}
		} else if (node instanceof Map<?, ?>) {
			if (!((Map<?, ?>) node).containsValue("red")) {
				for (Object o : ((Map<?, ?>) node).keySet()) {
					result += sumOfNumbers(((Map<?, ?>) node).get(o));
				}
			}
		} else {
			try {
				result += Float.parseFloat(node.toString());
			} catch (NumberFormatException e) {
			}
		}
		return result;
	}
}
