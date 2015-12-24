package net.logicsquad.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9 {
	private static final String INPUT_FILENAME = "etc/day9.input";

	public static void main(String[] args) throws IOException {
		Map<String, City> cities = new HashMap<String, City>();
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME), StandardCharsets.UTF_8);
		for (String line : lines) {
			City.parseLine(line, cities);
		}
		
		List<String> names = new ArrayList<String>();
		for (String name : cities.keySet()) {
			names.add(name);
		}
		Permutation p = new Permutation(names);

		int distance = 0;
		for (List<String> perm : p) {
			City current = null;
			City next = null;
			int candidate = 0;
			for (String s : perm) {
				next = cities.get(s);
				if (current != null) {
					candidate += current.distanceTo(next);
				}
				current = next;
			}
			if (distance == 0 || candidate < distance) {
				distance = candidate;
			}
		}
		System.out.println("Day9.main: distance = " + distance);
	}

	public static class City {
		public final String name;

		public Map<City, Integer> routes = new HashMap<City, Integer>();

		public City(String name) {
			this.name = name;
			return;
		}

		public void addRoute(City city, int distance) {
			routes.put(city, distance);
			return;
		}
		
		public int distanceTo(City city) {
			return routes.get(city);
		}
		
		public static void parseLine(String line, Map<String, City> cities) {
			Pattern p = Pattern.compile("^(.*) to (.*) = (.*)$");
			Matcher m = p.matcher(line);
			m.find();
			String from = m.group(1);
			String to = m.group(2);
			City fromCity = null;
			City toCity = null;
			int distance = Integer.parseInt(m.group(3));
			if (cities.containsKey(from)) {
				fromCity = cities.get(from);
			} else {
				fromCity = new City(from);
				cities.put(from, fromCity);
			}
			if (cities.containsKey(to)) {
				toCity = cities.get(to);
			} else {
				toCity = new City(to);
				cities.put(to, toCity);
			}
			toCity.addRoute(fromCity, distance);
			fromCity.addRoute(toCity, distance);
			return;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			sb.append(name);
			sb.append(": ");
			for (City c : routes.keySet()) {
				sb.append(c.name);
				sb.append("(");
				sb.append(routes.get(c));
				sb.append(") ");
			}
			sb.append("]");
			return sb.toString();
		}
	}

	public static class Permutation implements Iterable<List<String>> {
		private Iterator<List<String>> iterator;

		private final List<String> source;

		public Permutation(List<String> source) {
			this.source = source;
			return;
		}

		@Override
		public Iterator<List<String>> iterator() {
			if (iterator == null) {
				iterator = new StringListIterator();
			}
			return iterator;
		}

		private class StringListIterator implements Iterator<List<String>> {
			private final List<List<String>> list;
			private int next = 0;

			public StringListIterator() {
				list = permute(source);
			}

			@Override
			public boolean hasNext() {
				return next < list.size();
			}

			@Override
			public List<String> next() {
				List<String> result = list.get(next);
				next++;
				return result;
			}
			
			private List<List<String>> permute(List<String> source) {
				List<List<String>> result = new ArrayList<List<String>>();
				if (source.size() == 1) {
					result.add(source);
					return result;
				} else if (source.size() == 2) {
					List<String> list1 = new ArrayList<String>();
					list1.add(source.get(0));
					list1.add(source.get(1));
					List<String> list2 = new ArrayList<String>();
					list2.add(source.get(1));
					list2.add(source.get(0));
					result.add(list1);
					result.add(list2);
					return result;
				} else {
					for (String s : source) {
						List<String> perm = new ArrayList<String>(source);
						perm.remove(s);
						
						for (List<String> l : permute(perm)) {
							List<String> list = new ArrayList<String>();
							list.add(s);
							list.addAll(l);
							result.add(list);
						}
					}
				}
				return result;
			}
		}
	}
}
