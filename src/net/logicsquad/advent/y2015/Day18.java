package net.logicsquad.advent.y2015;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day18 {
	public static void main(String[] args) throws IOException {
		G g = new G(Files.readAllLines(Paths.get("etc/2015/day18.input"), StandardCharsets.UTF_8));
		for (int i = 0; i < 100; i++) g.t();
		System.out.println("Day18.main: grid.lightsOn() = " + g.o());
	}
	public static class G {
		private boolean[][] l = new boolean[100][100];
		public G(List<String> p) {
			for (int i = 0; i < 100; i++) for (int j = 0; j < 100; j++) if (p.get(i).charAt(j) == '#') l[i][j] = true;
			l[0][0] = true;
			l[0][99] = true;
			l[99][0] = true;
			l[99][99] = true;
		}
		private int n(int i, int j) {
			int c = 0;
			if (i - 1 > -1 && j - 1 > -1 && l[i - 1][j - 1]) c++;
			if (j - 1 > -1 && l[i][j - 1]) c++;
			if (i + 1 < 100 && j - 1 > -1 && l[i + 1][j - 1]) c++;
			if (i - 1 > -1 && l[i - 1][j]) c++;
			if (i + 1 < 100 && l[i + 1][j]) c++;
			if (i - 1 > -1 && j + 1 < 100 && l[i - 1][j + 1]) c++;
			if (j + 1 < 100 && l[i][j + 1]) c++;
			if (i + 1 < 100 && j + 1 < 100 && l[i + 1][j + 1]) c++;
			return c;
		}
		public void t() {
			boolean[][] x = new boolean[100][100];
			for (int i = 0; i < 100; i++)
				for (int j = 0; j < 100; j++) {
					int a = n(i, j);
					if (l[i][j]) {
						if (a == 2 || a == 3)
							x[i][j] = true;
					} else {
						if (a == 3)
							x[i][j] = true;
					}
				}
			l = x;
			l[0][0] = true;
			l[0][99] = true;
			l[99][0] = true;
			l[99][99] = true;
			return;
		}
		public int o() {
			int c = 0;
			for (int i = 0; i < 100; i++) for (int j = 0; j < 100; j++) if (l[i][j]) c++;
			return c;
		}
	}
}
