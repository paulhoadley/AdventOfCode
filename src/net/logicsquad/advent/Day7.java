package net.logicsquad.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
	private static final String INPUT_FILENAME = "etc/day7.input";
	
	public static void main(String[] args) throws IOException {
		// Wires
		Map<String, Integer> wires = new HashMap<String, Integer>();
		List<Connection> reference = new ArrayList<Connection>();
		List<Connection> unresolved = new ArrayList<Connection>();
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME), StandardCharsets.UTF_8);
		for (String line : lines) {
			reference.add(new Connection(line));
			unresolved.add(new Connection(line));
		}
		List<Connection> resolved = new ArrayList<Connection>();
		
		System.out.println("Day7.main: unresolved.size() = " + unresolved.size());
		System.out.println("Day7.main: resolved.size() = " + resolved.size());

		int lap = 0;
		while (unresolved.size() > 0) {
			for (Connection conn : reference) {
				int i = reference.indexOf(conn);
				conn.process(wires, unresolved, resolved);
				for (int j = 0; j < i; j++) {
					reference.get(j).process(wires, unresolved, resolved);
				}
			}
			reference = new ArrayList<Connection>();
			reference.addAll(unresolved);
			lap++;
			System.out.println("Day7.main: lap = " + lap);
			System.out.println("Day7.main: unresolved.size() = " + unresolved.size());
			System.out.println("Day7.main: resolved.size() = " + resolved.size());
		}
		System.out.println("Day7.main: -------------");
		System.out.println("Day7.main: lap = " + lap);
		System.out.println("Day7.main: unresolved.size() = " + unresolved.size());
		System.out.println("Day7.main: resolved.size() = " + resolved.size());

		System.out.println("Day7.main: -------------");
		System.out.println("Day7.main: a = " + wires.get("a"));
	}

	public static class Connection {
		private final String description;
		public final String wire;
		public Connection(String description) {
			this.description = description;
			Pattern p = Pattern.compile("^.*-> (.*)$");
			Matcher m = p.matcher(description);
			m.find();
			wire = m.group(1);
			return;
		}
		
		public void process(Map<String, Integer> wires, List<Connection> unresolved, List<Connection> resolved) {
			if (description.contains("AND")) {
				System.out.println("Day7.Connection.process: AND");
				Pattern p = Pattern.compile("^(.*) AND (.*) -> (.*)$");
				Matcher m = p.matcher(description);
				m.find();
				int input1 = -1, input2 = -1;
				try {
					input1 = Integer.parseInt(m.group(1));
					System.out.println("Day7.Connection.process: input1 = "
							+ input1);
				} catch (NumberFormatException e) {
					if (wires.get(m.group(1)) != null) {
						input1 = wires.get(m.group(1));
						System.out.println("Day7.Connection.process: input1 = "
								+ input1);
					}
				}
				try {
					input2 = Integer.parseInt(m.group(2));
					System.out.println("Day7.Connection.process: input2 = "
							+ input2);
				} catch (NumberFormatException e) {
					if (wires.get(m.group(2)) != null) {
						input2 = wires.get(m.group(2));
						System.out.println("Day7.Connection.process: input2 = "
								+ input2);
					}
				}
				if (input1 > -1 && input2 > -1) {
					System.out.println("Day7.Connection.process: MOVING AND!");
					int result = input1 & input2;
					if (result < 0) {
						result = 0;
					}
					if (result > 65535) {
						result = 65535;
					}
					wires.put(m.group(3), result);
					unresolved.remove(this);
					resolved.add(this);
				}
				return;
			} else if (description.contains("OR")) {
				System.out.println("Day7.Connection.process: OR");
				Pattern p = Pattern.compile("^(.*) OR (.*) -> (.*)$");
				Matcher m = p.matcher(description);
				m.find();
				int input1 = -1, input2 = -1;
				try {
					input1 = Integer.parseInt(m.group(1));
					System.out.println("Day7.Connection.process: input1 = "
							+ input1);
				} catch (NumberFormatException e) {
					if (wires.get(m.group(1)) != null) {
						input1 = wires.get(m.group(1));
						System.out.println("Day7.Connection.process: input1 = "
								+ input1);
					}
				}
				try {
					input2 = Integer.parseInt(m.group(2));
					System.out.println("Day7.Connection.process: input2 = "
							+ input2);
				} catch (NumberFormatException e) {
					if (wires.get(m.group(2)) != null) {
						input2 = wires.get(m.group(2));
						System.out.println("Day7.Connection.process: input2 = "
								+ input2);
					}
				}
				if (input1 > -1 && input2 > -1) {
					System.out.println("Day7.Connection.process: MOVING OR!");
					int result = input1 | input2;
					if (result < 0) {
						result = 0;
					}
					if (result > 65535) {
						result = 65535;
					}
					wires.put(m.group(3), result);
					unresolved.remove(this);
					resolved.add(this);
				}
				return;
			} else if (description.contains("NOT")) {
				System.out.println("Day7.Connection.process: NOT");
				Pattern p = Pattern.compile("^NOT (.*) -> (.*)$");
				Matcher m = p.matcher(description);
				m.find();
				int input1 = -1;
				try {
					input1 = Integer.parseInt(m.group(1));
					System.out.println("Day7.Connection.process: input1 = "
							+ input1);
				} catch (NumberFormatException e) {
					if (wires.get(m.group(1)) != null) {
						input1 = wires.get(m.group(1));
						System.out.println("Day7.Connection.process: input1 = "
								+ input1);
					}
				}
				if (input1 > -1) {
					System.out.println("Day7.Connection.process: MOVING NOT!");
					int result = 65535 - input1;
					if (result < 0) {
						result = 0;
					}
					if (result > 65535) {
						result = 65535;
					}
					wires.put(m.group(2), result);
					unresolved.remove(this);
					resolved.add(this);
				}
			} else if (description.contains("RSHIFT")) {
				System.out.println("Day7.Connection.process: RSHIFT");
				Pattern p = Pattern.compile("^(.*) RSHIFT (.*) -> (.*)$");
				Matcher m = p.matcher(description);
				m.find();
				int input1 = -1, input2 = -1;
				try {
					input1 = Integer.parseInt(m.group(1));
					System.out.println("Day7.Connection.process: input1 = "
							+ input1);
				} catch (NumberFormatException e) {
					if (wires.get(m.group(1)) != null) {
						input1 = wires.get(m.group(1));
						System.out.println("Day7.Connection.process: input1 = "
								+ input1);
					}
				}
				try {
					input2 = Integer.parseInt(m.group(2));
					System.out.println("Day7.Connection.process: input2 = "
							+ input2);
				} catch (NumberFormatException e) {
					if (wires.get(m.group(2)) != null) {
						input2 = wires.get(m.group(2));
						System.out.println("Day7.Connection.process: input2 = "
								+ input2);
					}
				}
				if (input1 > -1 && input2 > -1) {
					int result = input1 >> input2;
					if (result < 0) {
						result = 0;
					}
					if (result > 65535) {
						result = 65535;
					}
					System.out.println("Day7.Connection.process: MOVING RSHIFT!");
					wires.put(m.group(3), result);
					unresolved.remove(this);
					resolved.add(this);
				}
				return;
			} else if (description.contains("LSHIFT")) {
				System.out.println("Day7.Connection.process: LSHIFT");
				Pattern p = Pattern.compile("^(.*) LSHIFT (.*) -> (.*)$");
				Matcher m = p.matcher(description);
				m.find();
				int input1 = -1, input2 = -1;
				try {
					input1 = Integer.parseInt(m.group(1));
					System.out.println("Day7.Connection.process: input1 = "
							+ input1);
				} catch (NumberFormatException e) {
					if (wires.get(m.group(1)) != null) {
						input1 = wires.get(m.group(1));
						System.out.println("Day7.Connection.process: input1 = "
								+ input1);
					}
				}
				try {
					input2 = Integer.parseInt(m.group(2));
					System.out.println("Day7.Connection.process: input2 = "
							+ input2);
				} catch (NumberFormatException e) {
					if (wires.get(m.group(2)) != null) {
						input2 = wires.get(m.group(2));
						System.out.println("Day7.Connection.process: input2 = "
								+ input2);
					}
				}
				if (input1 > -1 && input2 > -1) {
					System.out.println("Day7.Connection.process: MOVING LSHIFT!");
					int result = input1 << input2;
					if (result < 0) {
						result = 0;
					}
					if (result > 65535) {
						result = 65535;
					}
					wires.put(m.group(3), result);
					unresolved.remove(this);
					resolved.add(this);
				}
				return;
			} else {
				// 0 -> c
				System.out.println("Day7.Connection.process: DIRECT");
				Pattern p = Pattern.compile("^(.*) -> (.*)$");
				Matcher m = p.matcher(description);
				m.find();
				int input1 = -1;
				try {
					input1 = Integer.parseInt(m.group(1));
					System.out.println("Day7.Connection.process: input1 = "
							+ input1);
				} catch (NumberFormatException e) {
					if (wires.get(m.group(1)) != null) {
						input1 = wires.get(m.group(1));
						System.out.println("Day7.Connection.process: input1 = "
								+ input1);
					}
				}
				if (input1 > -1) {
					System.out.println("Day7.Connection.process: MOVING DIRECT!");
					int result = input1;
					if (result < 0) {
						result = 0;
					}
					if (result > 65535) {
						result = 65535;
					}
					wires.put(m.group(2), result);
					unresolved.remove(this);
					resolved.add(this);
				}
			}
		}
	}
}
