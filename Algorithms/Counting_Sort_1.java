package Algorithms;

import java.util.Scanner;

public class Counting_Sort_1 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int[] count = new int[100];

		for (int i = 0; i < n; i++) {
			count[scn.nextInt()]++;
		}
		
		scn.close();

		for (int i = 0; i < count.length; i++) {
			System.out.print(count[i] + " ");
		}
	}
}
