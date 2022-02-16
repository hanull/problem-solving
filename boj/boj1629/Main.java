package boj.boj1629;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		System.out.println(pow(A, B, C));
	}

	private static long pow(int a, int b, int c) {
		if (b == 1) {
			return a % c;
		}
		if (b % 2 == 1) {
			return a * pow(a, b - 1, c) % c;
		}
		long temp = pow(a, b / 2, c);
		return temp * temp % c;
	}
}
