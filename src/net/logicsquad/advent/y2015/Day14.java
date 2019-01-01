package net.logicsquad.advent.y2015;

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

public class Day14 {
	private static final String INPUT_FILENAME = "etc/2015/day14.input";
	private static final int TICKS = 2503;

	public static void main(String[] args) throws IOException {
		List<Reindeer> stable = new ArrayList<Reindeer>();
		Map<Reindeer, Integer> board = new HashMap<Reindeer, Integer>();
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME),
				StandardCharsets.UTF_8);
		for (String line : lines) {
			stable.add(new Reindeer(line));
		}
		for (Reindeer r : stable) {
			board.put(r, 0);
		}
		for (int tick = 0; tick < TICKS; tick++) {
			for (Reindeer r : stable) {
				r.tick();
			}
			Reindeer max = null;
			for (Reindeer r : stable) {
				if (max == null || r.distance > max.distance) {
					max = r;
				}
			}
			board.put(max, board.get(max) + 1);
		}
		Reindeer winner = null;
		int points = 0;
		for (Reindeer r : board.keySet()) {
			if (winner == null || board.get(r) > points) {
				winner = r;
				points = board.get(r);
			}
		}
		System.out.println("Day14.main: points = " + board.get(winner));
	}

	public static class Reindeer {
		public final String name;
		public final int speed;
		public final int duration;
		public final int rest;
		private int tickDiff = 0;
		private State state = State.FLYING;
		public int distance = 0;

		private enum State {
			FLYING, RESTING;
		}

		public Reindeer(String line) {
			Pattern p = Pattern
					.compile("^(\\w*) can fly (\\d*) km/s for (\\d*) seconds, but then must rest for (\\d*) seconds.$");
			Matcher m = p.matcher(line);
			m.find();

			this.name = m.group(1);
			this.speed = Integer.parseInt(m.group(2));
			this.duration = Integer.parseInt(m.group(3));
			this.rest = Integer.parseInt(m.group(4));
			return;
		}

		public void tick() {
			tickDiff++;
			if (state == State.FLYING) {
				distance += speed;
				if (tickDiff == duration) {
					state = State.RESTING;
					tickDiff = 0;
				}
			} else {
				if (tickDiff == rest) {
					state = State.FLYING;
					tickDiff = 0;
				}
			}
		}
	}
}
