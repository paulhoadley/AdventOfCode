package net.logicsquad.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Day3 {
	private static final String INPUT_FILENAME = "etc/day3.input";

	public static void main(String[] args) throws IOException {
		// The House universe
		Map<CoOrdinates, House> grid = new HashMap<CoOrdinates, House>();
		// Create a new House at the origin
		House currentHouse = new House(grid, new CoOrdinates(0, 0));
		byte[] encoded = Files.readAllBytes(Paths.get(INPUT_FILENAME));
		String string = new String(encoded, "UTF-8");
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			currentHouse = currentHouse.process(c);
		}
		System.out.println("Day3.main: houses.size() = " + grid.size());
	}

	public static class CoOrdinates {
		public int x;
		public int y;

		public CoOrdinates(int x, int y) {
			this.x = x;
			this.y = y;
			return;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CoOrdinates other = (CoOrdinates) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}
	
	public static class House {
		public CoOrdinates coOrds;
		public Map<CoOrdinates, House> grid;
		
		public House(Map<CoOrdinates, House> grid, CoOrdinates coOrds) {
			this.grid = grid;
			this.coOrds = coOrds;
			// Put the House on the grid at the supplied CoOrdinates
			grid.put(coOrds, this);
			return;
		}
		
		public House process(char c) {
			switch (c) {
			case '^':
				// north
				House north = grid.get(coOrdinatesInDirection(Direction.NORTH));
				if (north == null) {
					north = new House(grid, coOrdinatesInDirection(Direction.NORTH));
					grid.put(north.coOrds, north);
				}
				return north;
			case 'v':
				House south = grid.get(coOrdinatesInDirection(Direction.SOUTH));
				if (south == null) {
					south = new House(grid, coOrdinatesInDirection(Direction.SOUTH));
					grid.put(south.coOrds, south);
				}
				return south;
			case '<':
				House west = grid.get(coOrdinatesInDirection(Direction.WEST));
				if (west == null) {
					west = new House(grid, coOrdinatesInDirection(Direction.WEST));
					grid.put(west.coOrds, west);
				}
				return west;
			case '>':
				House east = grid.get(coOrdinatesInDirection(Direction.EAST));
				if (east == null) {
					east = new House(grid, coOrdinatesInDirection(Direction.EAST));
					grid.put(east.coOrds, east);
				}
				return east;
			default:
				return null;
			}
		}

		public enum Direction {
			NORTH, SOUTH, WEST, EAST;
		}
		
		public CoOrdinates coOrdinatesInDirection(Direction direction) {
			switch (direction) {
			case NORTH:
				return new CoOrdinates(coOrds.x, coOrds.y + 1);
			case SOUTH:
				return new CoOrdinates(coOrds.x, coOrds.y - 1);
			case WEST:
				return new CoOrdinates(coOrds.x - 1, coOrds.y);
			case EAST:
				return new CoOrdinates(coOrds.x + 1, coOrds.y);
			default:
				return null;
			}
		}
	}
}
