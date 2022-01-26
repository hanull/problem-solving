package boj.boj2138;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int N;
	private static final char ON = '1';
	private static final char OFF = '0';
	private static int MAX = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[] bulbA = br.readLine().toCharArray();
		char[] bulbB = new char[N];
		System.arraycopy(bulbA, 0, bulbB, 0, bulbA.length);
		bulbB[0] = change(bulbB[0]);
		bulbB[1] = change(bulbB[1]);
		char[] targetBulb = br.readLine().toCharArray();
		int countBulb = makeTargetBulb(bulbA, targetBulb);
		int countCheckedFirstBulb = makeTargetBulb(bulbB, targetBulb) + 1;
		if (countBulb == MAX && countCheckedFirstBulb - 1 == MAX) {
			System.out.println(-1);
		} else {
			System.out.println(Math.min(countBulb, countCheckedFirstBulb));
		}
	}

	private static int makeTargetBulb(char[] bulb, char[] targetBulb) {
		int count = 0;
		for (int i = 1; i < N; i++) {
			if (bulb[i - 1] != targetBulb[i - 1]) {
				count++;
				bulb[i - 1] = change(bulb[i - 1]);
				bulb[i] = change(bulb[i]);
				if (i < N - 1) {
					bulb[i + 1] = change(bulb[i + 1]);
				}
			}
		}
		if (new String(bulb).equals(new String(targetBulb))) {
			return count;
		}
		return MAX;
	}

	private static char change(char bulb) {
		return bulb == ON ? OFF : ON;
	}
}
