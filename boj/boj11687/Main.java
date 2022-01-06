package boj.boj11687;

import java.util.Scanner;

public class Main {

	private static final int MAX = 100000000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int targetZeroCount = sc.nextInt();
		System.out.println(solve(targetZeroCount));
	}

	private static int solve(int targetZeroCount) {
		int left = 1;
		int right = MAX * 5;
		int answer = -1;
		while (left <= right) {
			int mid = (left + right) / 2;
			int count = 0;
			for (int i = 5; i <= mid; i *= 5) {
				count += mid / i;
			}
			if (count < targetZeroCount) {
				left = mid + 1;
			} else {
				if (count == targetZeroCount) {
					answer = mid;
				}
				right = mid - 1;
			}
		}
		return answer;
	}
}
