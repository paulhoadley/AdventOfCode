package net.logicsquad.advent.y2015;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
	private static final String INPUT_FILENAME = "etc/2015/day7.input";

	public static void main(String[] args) throws IOException {
		Map<String, Integer> wires = new HashMap<String, Integer>();
		Queue<Connection> queue = new LinkedList<Connection>();
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME),
				StandardCharsets.UTF_8);
		for (String line : lines) {
			if (line.endsWith("-> b")) {
				line = "956 -> b";
			}
			queue.add(new Connection(line));
		}
		while (queue.size() > 0) {
			Connection conn = queue.remove();
			conn.process(wires, queue);
		}
		System.out.println("Day7.main: a = " + wires.get("a"));
	}

	public static class Connection {
		private final String description;

		public Connection(String description) {
			this.description = description;
			return;
		}

		public void process(Map<String, Integer> wires, Queue<Connection> queue) {
			if (description.contains("AND")) {
				Pattern p = Pattern.compile("^(.*) AND (.*) -> (.*)$");
				Matcher m = p.matcher(description);
				m.find();
				int input1 = integerForSymbol(m.group(1), wires);
				int input2 = integerForSymbol(m.group(2), wires);
				if (input1 > -1 && input2 > -1) {
					wires.put(m.group(3), input1 & input2);
				} else {
					queue.add(this);
				}
				return;
			} else if (description.contains("OR")) {
				Pattern p = Pattern.compile("^(.*) OR (.*) -> (.*)$");
				Matcher m = p.matcher(description);
				m.find();
				int input1 = integerForSymbol(m.group(1), wires);
				int input2 = integerForSymbol(m.group(2), wires);
				if (input1 > -1 && input2 > -1) {
					wires.put(m.group(3), input1 | input2);
				} else {
					queue.add(this);
				}
				return;
			} else if (description.contains("NOT")) {
				Pattern p = Pattern.compile("^NOT (.*) -> (.*)$");
				Matcher m = p.matcher(description);
				m.find();
				int input1 = integerForSymbol(m.group(1), wires);
				if (input1 > -1) {
					wires.put(m.group(2), 65535 - input1);
				} else {
					queue.add(this);
				}
			} else if (description.contains("RSHIFT")) {
				Pattern p = Pattern.compile("^(.*) RSHIFT (.*) -> (.*)$");
				Matcher m = p.matcher(description);
				m.find();
				int input1 = integerForSymbol(m.group(1), wires);
				int input2 = integerForSymbol(m.group(2), wires);
				if (input1 > -1 && input2 > -1) {
					wires.put(m.group(3), input1 >> input2);
				} else {
					queue.add(this);
				}
				return;
			} else if (description.contains("LSHIFT")) {
				Pattern p = Pattern.compile("^(.*) LSHIFT (.*) -> (.*)$");
				Matcher m = p.matcher(description);
				m.find();
				int input1 = integerForSymbol(m.group(1), wires);
				int input2 = integerForSymbol(m.group(2), wires);
				if (input1 > -1 && input2 > -1) {
					wires.put(m.group(3), input1 << input2);
				} else {
					queue.add(this);
				}
				return;
			} else {
				Pattern p = Pattern.compile("^(.*) -> (.*)$");
				Matcher m = p.matcher(description);
				m.find();
				int input1 = integerForSymbol(m.group(1), wires);
				if (input1 > -1) {
					wires.put(m.group(2), input1);
				} else {
					queue.add(this);
				}
			}
		}

		private int integerForSymbol(String symbol, Map<String, Integer> wires) {
			int result = -1;
			try {
				result = Integer.parseInt(symbol);
			} catch (NumberFormatException e) {
				if (wires.get(symbol) != null) {
					result = wires.get(symbol);
				}
			}
			return result;
		}
	}
}
