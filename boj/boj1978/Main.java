package boj.boj1978;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        boolean[] prime = new boolean[1001];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 2; i <= Math.sqrt(1000); i++) {
            for (int j = i * i; j <= 1000; j += i) {
                if (prime[j]) prime[j] = false;
            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (prime[sc.nextInt()]) cnt++;
        }
        System.out.println(cnt);
    }
}
