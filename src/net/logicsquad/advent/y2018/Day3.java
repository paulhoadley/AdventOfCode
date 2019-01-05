package net.logicsquad.advent.y2018;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends Day {
	private static final String INPUT_FILENAME = "etc/2018/day3.input";

	public static void main(String[] args) {
		Pattern p = Pattern.compile("-?\\d+");
		for (String line : linesForFilename(INPUT_FILENAME)) {
			Matcher m = p.matcher(line);
			int[] vals = new int[5];
			for (int i = 0; m.find(); i++) {
				vals[i] = Integer.parseInt(m.group());
			}
			Area a = new Area(vals[0], vals[1], vals[2], vals[3], vals[4]);
			System.out.println("Day3.main: " + a);
		}
		return;
	}

	private static class Area {
		public final int id;
		public final int x;
		public final int y;
		public final int w;
		public final int h;

		public Area(int id, int x, int y, int w, int h) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			return;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(id);
			sb.append(' ');
			sb.append(x);
			sb.append(' ');
			sb.append(y);
			sb.append(' ');
			sb.append(w);
			sb.append(' ');
			sb.append(h);
			return sb.toString();
		}
	}
}
