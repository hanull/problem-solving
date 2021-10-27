package boj.boj20444;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long k = sc.nextLong();
        long left = 0;
        long right = n / 2;
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = (mid + 1) * (n - mid + 1);
            if (count == k) {
                System.out.println("YES");
                System.exit(0);
            } else if (count > k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println("NO");
    }
}
