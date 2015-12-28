package net.logicsquad.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 {
	private static final String INPUT_FILENAME = "etc/day15.input";

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(INPUT_FILENAME),
				StandardCharsets.UTF_8);
		Ingredient sugar = Ingredient.parse(lines.get(0));
		Ingredient sprinkles = Ingredient.parse(lines.get(1));
		Ingredient candy = Ingredient.parse(lines.get(2));
		Ingredient chocolate = Ingredient.parse(lines.get(3));

		int max = 0;
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100 - i; j++) {
				for (int k = 0; k <= 100 - i - j; k++) {
					int l = 100 - i - j - k;
					int capacity = i * sugar.capacity + j * sprinkles.capacity + k * candy.capacity + l * chocolate.capacity;
					if (capacity < 0) {
						capacity = 0;
					}
					int durability = i * sugar.durability + j * sprinkles.durability + k * candy.durability + l * chocolate.durability;
					if (durability < 0) {
						durability = 0;
					}
					int flavor = i * sugar.flavor + j * sprinkles.flavor + k * candy.flavor + l * chocolate.flavor;
					if (flavor < 0) {
						flavor = 0;
					}
					int texture = i * sugar.texture + j * sprinkles.texture + k * candy.texture + l * chocolate.texture;
					if (texture < 0) {
						texture = 0;
					}
					int calories = i * sugar.calories + j * sprinkles.calories + k * candy.calories + l * chocolate.calories;
					if (calories < 0) {
						calories = 0;
					}
					if (calories == 500) {
						int result = capacity * durability * flavor * texture;
						if (result > max) {
							max = result;
						}
					}
				}
			}
		}
		System.out.println("Day15.main: max = " + max);
	}

	public static class Ingredient {
		public final int capacity;
		public final int durability;
		public final int flavor;
		public final int texture;
		public final int calories;
		
		public Ingredient(int capacity, int durability, int flavor, int texture, int calories) {
			this.capacity = capacity;
			this.durability = durability;
			this.flavor = flavor;
			this.texture = texture;
			this.calories = calories;
			return;
		}

		public static Ingredient parse(String line) {
			Pattern p = Pattern.compile("^\\w*: capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)$");
			Matcher m = p.matcher(line);
			m.find();
			return new Ingredient(Integer.parseInt(m.group(1)),
					Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)),
					Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)));
		}
	}
}
