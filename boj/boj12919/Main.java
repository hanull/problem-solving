package boj.boj12919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String targetString = br.readLine();
		String input = br.readLine();
		if (input.length() == targetString.length()) {
			System.out.println(input.equals(targetString) ? 1 : 0);
		} else {
			solve(input, targetString);
			System.out.println(0);
		}
	}

	private static void solve(String input, String targetString) {
		if (input.length() == targetString.length()) {
			if (input.equals(targetString)) {
				System.out.println(1);
				System.exit(0);
			}
			return;
		}
		if (input.charAt(input.length() - 1) == 'A') {
			solve(input.substring(0, input.length() - 1), targetString);
		}
		if (input.charAt(0) == 'B') {
			solve(new StringBuilder(input).reverse().substring(0, input.length() - 1), targetString);
		}
	}

}
