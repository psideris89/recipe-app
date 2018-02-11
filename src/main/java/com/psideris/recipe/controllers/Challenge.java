package com.psideris.recipe.controllers;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Challenge {

	public static void main(final String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		String[] pair_left = new String[t];
		String[] pair_right = new String[t];

		Set<String> as = new HashSet<>();

		for (int i = 0; i < t; i++) {
			pair_left[i] = in.next();
			pair_right[i] = in.next();
		}

		in.close();
	}
}
