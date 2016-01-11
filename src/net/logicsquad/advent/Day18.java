package net.logicsquad.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import net.logicsquad.advent.Day18.Light.State;

public class Day18 {
	private static final String INPUT_FILENAME = "etc/day18.input";

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME), StandardCharsets.UTF_8);
		Grid grid = new Grid(lines);
		for (int i = 0; i < 100; i++) {
			grid.tick();
		}
		System.out.println("Day18.main: grid.lightsOn() = " + grid.lightsOn());
	}

	public static class Light {
		public State state;

		public enum State {
			ON, OFF;
		}

		public Light(State state) {
			this.state = state;
		}
	}

	public static class Grid {
		private Light[][] lights = new Light[100][100];

		public Grid(List<String> input) {
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					char c = input.get(i).charAt(j);
					if (c == '#') {
						lights[i][j] = new Light(State.ON);
					} else {
						lights[i][j] = new Light(State.OFF);
					}
				}
			}
		}

		private int neighboursAlight(int i, int j) {
			int count = 0;
			if (i - 1 > -1 && j - 1 > -1 && lights[i - 1][j - 1].state == State.ON) {
				count++;
			}
			if (j - 1 > -1 && lights[i][j - 1].state == State.ON) {
				count++;
			}
			if (i + 1 < 100 && j - 1 > -1 && lights[i + 1][j - 1].state == State.ON) {
				count++;
			}
			if (i - 1 > -1 && lights[i - 1][j].state == State.ON) {
				count++;
			}
			if (i + 1 < 100 && lights[i + 1][j].state == State.ON) {
				count++;
			}
			if (i - 1 > -1 && j + 1 < 100 && lights[i - 1][j + 1].state == State.ON) {
				count++;
			}
			if (j + 1 < 100 && lights[i][j + 1].state == State.ON) {
				count++;
			}
			if (i + 1 < 100 && j + 1 < 100 && lights[i + 1][j + 1].state == State.ON) {
				count++;
			}
			return count;
		}

		public void tick() {
			Light[][] next = new Light[100][100];
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					int alight = neighboursAlight(i, j);
					if (lights[i][j].state == State.ON) {
						if (alight == 2 || alight == 3) {
							next[i][j] = new Light(State.ON);
						} else {
							next[i][j] = new Light(State.OFF);
						}
					} else {
						if (alight == 3) {
							next[i][j] = new Light(State.ON);
						} else {
							next[i][j] = new Light(State.OFF);
						}
					}
				}
			}
			lights = next;
			return;
		}

		public int lightsOn() {
			int count = 0;
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					if (lights[i][j].state == State.ON) {
						count++;
					}
				}
			}
			return count;
		}
	}
}
