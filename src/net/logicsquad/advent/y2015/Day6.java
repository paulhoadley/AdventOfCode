package net.logicsquad.advent.y2015;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.logicsquad.advent.y2015.Day6.Event.Action;

public class Day6 {
	private static final String INPUT_FILENAME = "etc/2015/day6.input";

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME), StandardCharsets.UTF_8);
		Grid grid = new Grid();
		for (String line : lines) {
			grid.process(Event.parse(line));
		}
		System.out.println("Day6.main: grid.litCount() = " + grid.litCount());
	}

	public static class Light {
		public int brightness = 0;

		public void handle(Action action) {
			switch (action) {
			case TURN_ON:
				brightness += 1;
				break;
			case TURN_OFF:
				brightness -= 1;
				if (brightness < 0) {
					brightness = 0;
				}
				break;
			case TOGGLE:
				brightness += 2;
				break;
			default:
				break;
			}
		}
	}

	public static class Grid {
		private Light[][] grid = new Light[1000][1000];
		
		public Grid() {
			for (int i = 0; i < 1000; i++) {
				for (int j = 0; j < 1000; j++) {
					grid[i][j] = new Light();
				}
			}
			return;
		}

		public int litCount() {
			int result = 0;
			for (int i = 0; i < 1000; i++) {
				for (int j = 0; j < 1000; j++) {
					result += grid[i][j].brightness;
				}
			}
			return result;
		}

		public void process(Event event) {
			for (int x = event.startX; x <= event.endX; x++) {
				for (int y = event.startY; y <= event.endY; y++) {
					grid[x][y].handle(event.action);
				}
			}
		}
	}

	public static class Event {
		public enum Action {
			TURN_ON, TURN_OFF, TOGGLE;
		}

		public Action action;
		public int startX;
		public int startY;
		public int endX;
		public int endY;
		
		public static Event parse(String s) {
			Event result = new Event();
			Pattern p = Pattern.compile("^(turn off|turn on|toggle) (\\d*),(\\d*) through (\\d*),(\\d*)");
			Matcher m = p.matcher(s);
			m.find();

			String action = m.group(1);
			if ("turn on".equals(action)) {
				result.action = Action.TURN_ON;
			} else if ("turn off".equals(action)) {
				result.action = Action.TURN_OFF;
			} else {
				result.action = Action.TOGGLE;
			}

			result.startX = Integer.valueOf(m.group(2));
			result.startY = Integer.valueOf(m.group(3));
			result.endX = Integer.valueOf(m.group(4));
			result.endY = Integer.valueOf(m.group(5));
			return result;
		}
	}
}
