package boj.boj9020;

import java.util.Arrays;
import java.util.Scanner;

public class Main {


    static final int max = 10000;
    static boolean[] primeNumber;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        makePrime();
        for (int t = 0; t < T; t++) {
            int n = sc.nextInt();
            int left = 2;
            int right = n;
            int res1 = 0;
            int res2 = 0;
            while (left <= right) {
                if (!primeNumber[left]) left++;
                else if (!primeNumber[right]) {
                    right--;
                } else {
                    int hap = left + right;
                    if (hap == n) {
                        res1 = left;
                        res2 = right;
                    }
                    if (hap >= n) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
            System.out.println(res1 + " " + res2);
        }
    }

    private static void makePrime() {
        primeNumber = new boolean[max + 1];
        Arrays.fill(primeNumber, true);
        primeNumber[0] = primeNumber[1] = false;
        for (int i = 2; i <= Math.sqrt(max); i++) {
            for (int j = i * i; j <= max; j += i) {
                if (primeNumber[j]) primeNumber[j] = false;
            }
        }
    }

}
