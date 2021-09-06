package boj.boj14916;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n % 5 == 0) {
            System.out.println(n / 5);
        } else {
            int a = n / 5;
            while (a >= 0) {
                int b = (n - a * 5) / 2;
                int total = a * 5 + b * 2;
                if (total == n) {
                    System.out.println(a + b);
                    System.exit(0);
                }
                a--;
            }
            System.out.println(-1);
        }
    }
}
