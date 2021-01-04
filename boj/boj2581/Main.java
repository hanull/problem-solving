package boj.boj2581;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        boolean[] prime = new boolean[N + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 2; i <= Math.sqrt(N); i++) {
            for (int j = i * i; j <= N; j += i) {
                if (prime[j]) prime[j] = false;
            }
        }
        int total = 0;
        int num = 0;
        boolean flag = false;
        for (int i = M; i <= N; i++) {
            if (prime[i]) {
                total += i;
                if (!flag) {
                    num = i;
                    flag = true;
                }
            }
        }
        if (flag) {
            System.out.println(total);
            System.out.println(num);
        } else {
            System.out.println(-1);
        }
    }

}
