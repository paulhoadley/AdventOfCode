package net.logicsquad.advent.y2015;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day2 {
	private static final String INPUT_FILENAME = "etc/2015/day2.input";

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME), StandardCharsets.UTF_8);
		int totalPaper = 0;
		int totalRibbon = 0;

		for (String line : lines) {
			Box b = new Box(line);
			totalPaper += b.paperRequired();
			totalRibbon += b.ribbonRequired();
		}
		System.out.println("Day2.main: totalPaper = " + totalPaper);
		System.out.println("Day2.main: totalRibbon = " + totalRibbon);
		return;
	}

	public static class Box {
		private final String descriptor;
		private final int length;
		private final int width;
		private final int height;
		
		public Box(String descriptor) {
			this.descriptor = descriptor;
			String[] components = descriptor.split("x");
			length = Integer.valueOf(components[0]).intValue();
			width = Integer.valueOf(components[1]).intValue();
			height = Integer.valueOf(components[2]).intValue();
			return;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(descriptor);
			sb.append(" -> ");
			sb.append(length);
			sb.append(", ");
			sb.append(width);
			sb.append(", ");
			sb.append(height);
			return sb.toString();
		}

		private int areaOfSmallestSide() {
			int[] sides = new int[] { length, width, height };
			Arrays.sort(sides);
			return sides[0] * sides[1];
		}
		
		private int surfaceArea() {
			return 2 * length * width + 2 * width * height + 2 * length * height;
		}
		
		public int paperRequired() {
			return surfaceArea() + areaOfSmallestSide();
		}

		private int smallestPerimeter() {
			int[] sides = new int[] { length, width, height };
			Arrays.sort(sides);
			return 2 * sides[0] + 2 * sides[1];
		}

		private int volume() {
			return length * width * height;
		}
		
		public int ribbonRequired() {
			return smallestPerimeter() + volume();
		}
	}
}
