package boj.boj2312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	static List<Integer> primes = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = stoi(br.readLine());
		setPrime();
		for (int i = 0; i < N; i++) {
			int number = stoi(br.readLine());
			System.out.print(calculate(number));
		}
	}

	private static String calculate(int number) {
		StringBuilder answer = new StringBuilder();
		for (Integer prime : primes) {
			int count = 0;
			while (number != 0 && number % prime == 0) {
				number = number / prime;
				count++;
			}
			if (count == 0) {
				continue;
			}
			answer.append(prime)
				.append(" ")
				.append(count)
				.append("\n");
		}
		return answer.toString();
	}

	private static void setPrime() {
		boolean[] prime = new boolean[100001];
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
		for (int i = 2; i <= Math.sqrt(100000); i++) {
			for (int j = i * i; j <= 100000; j += i) {
				if (prime[j]) {
					prime[j] = false;
				}
			}
		}
		for (int i = 2; i <= 100000; i++) {
			if (prime[i]) {
				primes.add(i);
			}
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
