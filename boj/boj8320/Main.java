package boj.boj8320;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cnt = 0;
        for (int i = 1; i <= Math.sqrt(n); i++) {
            for (int j = i; i * j <= n; j++) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
