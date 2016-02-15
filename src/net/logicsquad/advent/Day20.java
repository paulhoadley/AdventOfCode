package net.logicsquad.advent;

public class Day20 {
	private static final int MIN_SIZE = 34000000;

	public static void main(String[] args) {
		long start = System.nanoTime();
		int house = 1;
		while (true) {
			int total = 0;
			for (int elf = 1; elf < 51; elf++) {
				if (house % elf == 0) {
					total += 11 * house / elf;
				}
			}
			if (total >= MIN_SIZE) {
				break;
			}
			house++;
		}
		System.out.println("Day20.main: house = " + house);
		long end = System.nanoTime();
		float duration = (end - start) / 1000000;
		System.out.println("Day20.main: duration = " + duration);
	}
}
