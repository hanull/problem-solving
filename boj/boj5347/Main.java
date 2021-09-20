package boj.boj5347;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            long a = sc.nextInt();
            long b = sc.nextInt();
            long gcd = gcd(a, b);
            long lcm = a * b / gcd;
            System.out.println(lcm);
        }
    }

    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
