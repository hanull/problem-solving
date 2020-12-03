package boj.boj1644;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] prime = new boolean[n + 1];
        int[] total = new int[4000001];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            for (int j = i * i; j <= n; j += i) {
                if (prime[j]) prime[j] = false;
            }
        }
        for (int i = 2; i <= n; i++) {
            if (!prime[i]) continue;
            int cnt = i;
            for (int j = i + 1; j <= n; j++) {
                if (!prime[j]) continue;
                if (cnt + j > n) break;
                cnt += j;
                total[cnt] += 1;
            }
        }
        if (prime[n]) System.out.println(total[n] + 1);
        else System.out.println(total[n]);
    }

}
