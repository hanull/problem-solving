package boj.boj2531;

import java.util.Scanner;

public class Main {

    static int N, D, K, C;
    static int[] sushi, count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        D = sc.nextInt();
        K = sc.nextInt();
        C = sc.nextInt() - 1;
        sushi = new int[N * 2];
        count = new int[D];
        for (int i = 0; i < N; i++) {
            sushi[i] = sc.nextInt() - 1;
        }
        int idx = 0;
        for (int i = N; i < 2 * N; i++) {
            sushi[i] = sushi[idx++];
        }
        int total = 0;
        for (int i = 0; i < K; i++) {
            count[sushi[i]]++;
            if (count[sushi[i]] == 1) {
                total++;
            }
        }
        int answer = total;
        for (int i = 0; i < N; i++) {
            count[sushi[i]]--;
            if (count[sushi[i]] == 0) total--;
            count[sushi[i + K]]++;
            if (count[sushi[i + K]] == 1) total++;
            answer = Math.max(answer, count[C] == 0 ? total + 1 : total);
        }
        System.out.println(answer);
    }
}
