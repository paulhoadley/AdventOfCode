package net.logicsquad.advent.y2015;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16 {
	private static final String INPUT_FILENAME = "etc/2015/day16.input";
	
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME), StandardCharsets.UTF_8);
		List<Sue> sues = new ArrayList<Sue>();
		for (String line : lines) {
			Sue sue = new Sue(line);
			sues.add(sue);
		}
		List<Sue> reference = new ArrayList<Sue>(sues);
		for (Sue sue : reference) {
			if (!(sue.children == 3 || sue.children == -1)) {
				sues.remove(sue);
			}
			if (!(sue.cats > 7 || sue.cats == -1)) {
				sues.remove(sue);
			}
			if (!(sue.samoyeds == 2 || sue.samoyeds == -1)) {
				sues.remove(sue);
			}
			if (!(sue.pomeranians < 3 || sue.pomeranians == -1)) {
				sues.remove(sue);
			}
			if (!(sue.akitas == 0 || sue.akitas == -1)) {
				sues.remove(sue);
			}
			if (!(sue.vizslas == 0 || sue.vizslas == -1)) {
				sues.remove(sue);
			}
			if (!(sue.goldfish < 5 || sue.goldfish == -1)) {
				sues.remove(sue);
			}
			if (!(sue.trees > 3 || sue.trees == -1)) {
				sues.remove(sue);
			}
			if (!(sue.cars == 2 || sue.cars == -1)) {
				sues.remove(sue);
			}
			if (!(sue.perfumes == 1 || sue.perfumes == -1)) {
				sues.remove(sue);
			}
		}
		System.out.println("Day16.main: sues = " + sues);
		return;
	}

	public static class Sue {
		public int number;

		public int children = -1;
		public int cats = -1;
		public int samoyeds = -1;
		public int pomeranians = -1;
		public int akitas = -1;
		public int vizslas = -1;
		public int goldfish = -1;
		public int trees = -1;
		public int cars = -1;
		public int perfumes = -1;
		
		public Sue(String line) {
			Pattern p = Pattern.compile("Sue (\\d+): (\\w+): (\\d+), (\\w+): (\\d+), (\\w+): (\\d+)");
			Matcher m = p.matcher(line);
			m.find();
			number = Integer.parseInt(m.group(1));
			setAttribute(m.group(2), Integer.parseInt(m.group(3)));
			setAttribute(m.group(4), Integer.parseInt(m.group(5)));
			setAttribute(m.group(6), Integer.parseInt(m.group(7)));
			return;
		}

		private void setAttribute(String attribute, int value) {
			if ("children".equals(attribute)) {
				children = value;
			} else if ("cats".equals(attribute)) {
				cats = value;
			} else if ("samoyeds".equals(attribute)) {
				samoyeds = value;
			} else if ("pomeranians".equals(attribute)) {
				pomeranians = value;
			} else if ("akitas".equals(attribute)) {
				akitas = value;
			} else if ("vizslas".equals(attribute)) {
				vizslas = value;
			} else if ("goldfish".equals(attribute)) {
				goldfish = value;
			} else if ("trees".equals(attribute)) {
				trees = value;
			} else if ("cars".equals(attribute)) {
				cars = value;
			} else if ("perfumes".equals(attribute)) {
				perfumes = value;
			} else {
				throw new IllegalArgumentException("Unknown attribute: '" + attribute + "'");
			}
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Sue ");
			sb.append(number);
			sb.append(": ");
			if (children > -1) {
				sb.append("children: ");
				sb.append(children);
				sb.append(", ");
			}
			if (cats > -1) {
				sb.append("cats: ");
				sb.append(cats);
				sb.append(", ");
			}
			if (samoyeds > -1) {
				sb.append("samoyeds: ");
				sb.append(samoyeds);
				sb.append(", ");
			}
			if (pomeranians > -1) {
				sb.append("pomeranians: ");
				sb.append(pomeranians);
				sb.append(", ");
			}
			if (akitas > -1) {
				sb.append("akitas: ");
				sb.append(akitas);
				sb.append(", ");
			}
			if (vizslas > -1) {
				sb.append("vizslas: ");
				sb.append(vizslas);
				sb.append(", ");
			}
			if (goldfish > -1) {
				sb.append("goldfish: ");
				sb.append(goldfish);
				sb.append(", ");
			}
			if (trees > -1) {
				sb.append("trees: ");
				sb.append(trees);
				sb.append(", ");
			}
			if (cars > -1) {
				sb.append("cars: ");
				sb.append(cars);
				sb.append(", ");
			}
			if (perfumes > -1) {
				sb.append("perfumes: ");
				sb.append(perfumes);
				sb.append(", ");
			}
			String result = sb.toString();
			return result.substring(0, result.length() - 2);
		}
	}
}
