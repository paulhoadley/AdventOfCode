package net.logicsquad.advent.y2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class Day {
	protected static List<String> linesForFilename(String filename) {
		try {
			return Files.readAllLines(Paths.get(filename));
		} catch (IOException e) {
			return null;
		}
	}
}
