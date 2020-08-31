package boj.boj2581;

import java.util.Scanner;

public class Main {

    static boolean[] prime;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int min = 2;
        int res = 0;
        prime = new boolean[N + 1];

        prime[0] = true;
        prime[1] = true;
        for (int i = 2; i <= Math.sqrt(M); i++) {
            if (prime[i]) continue;
            for (int j = i * i; j <= N; j+=i) {
                prime[j] = true;
            }
        }
        for (int i = M; i <= N; i++) {
            if (!prime[i]) {
                res += i;
                if (min == 2) {
                    min = i;
                }
            }
        }
        if (res == 0) {
            System.out.println(-1);
        } else {
            System.out.println(res);
            System.out.println(min);
        }
    }

}
