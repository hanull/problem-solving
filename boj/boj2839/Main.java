package boj.boj2839;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int total = 0;
        int light = 0;
        boolean flag = false;
        for (int heavy = N / 5; heavy >= 0; heavy--) {
            total = N;
            total -= heavy * 5;
            light = total / 3;
            total -= light * 3;
            if (total == 0) {
                System.out.println(heavy + light);
                flag = true;
                break;
            }
        }
        if (!flag) System.out.println(-1);
    }
}
