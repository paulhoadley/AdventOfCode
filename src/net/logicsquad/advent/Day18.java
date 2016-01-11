package net.logicsquad.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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

	public static class Grid {
		private boolean[][] lights = new boolean[100][100];

		public Grid(List<String> input) {
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					char c = input.get(i).charAt(j);
					if (c == '#') {
						lights[i][j] = true;
					} else {
						lights[i][j] = false;
					}
				}
			}
		}

		private int neighboursAlight(int i, int j) {
			int count = 0;
			if (i - 1 > -1 && j - 1 > -1 && lights[i - 1][j - 1] == true) {
				count++;
			}
			if (j - 1 > -1 && lights[i][j - 1] == true) {
				count++;
			}
			if (i + 1 < 100 && j - 1 > -1 && lights[i + 1][j - 1] == true) {
				count++;
			}
			if (i - 1 > -1 && lights[i - 1][j] == true) {
				count++;
			}
			if (i + 1 < 100 && lights[i + 1][j] == true) {
				count++;
			}
			if (i - 1 > -1 && j + 1 < 100 && lights[i - 1][j + 1] == true) {
				count++;
			}
			if (j + 1 < 100 && lights[i][j + 1] == true) {
				count++;
			}
			if (i + 1 < 100 && j + 1 < 100 && lights[i + 1][j + 1] == true) {
				count++;
			}
			return count;
		}

		public void tick() {
			boolean[][] next = new boolean[100][100];
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					int alight = neighboursAlight(i, j);
					if (lights[i][j] == true) {
						if (alight == 2 || alight == 3) {
							next[i][j] = true;
						} else {
							next[i][j] = false;
						}
					} else {
						if (alight == 3) {
							next[i][j] = true;
						} else {
							next[i][j] = false;
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
					if (lights[i][j] == true) {
						count++;
					}
				}
			}
			return count;
		}
	}
}
