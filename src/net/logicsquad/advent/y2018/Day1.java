package net.logicsquad.advent.y2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1 {
	private static final String INPUT_FILENAME = "etc/2018/day1.input";

	public static void main(String args[]) throws IOException {
		System.out.println("sum = " + Files.lines(Paths.get(INPUT_FILENAME)).mapToInt(Integer::valueOf).sum());
		return;
	}
}
