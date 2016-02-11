package net.logicsquad.advent;

public class Day20 {
	private static final int MIN_SIZE = 34000000;
	
	public static void main(String[] args) {
		int house = 1;
		while (true) {
			int total = 0;
			for (int elf = 1; elf <= house; elf++) {
				if (house % elf == 0) {
					total += elf * 10;
				}
			}
			if (total > MIN_SIZE) {
				break;
			}
			house++;
		}
		System.out.println("Day20.main: house = " + house);
	}
}
