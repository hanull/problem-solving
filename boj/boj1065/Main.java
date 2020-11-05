package boj.boj1065;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if (N < 100) {
            System.out.println(N);
        } else if (N < 111) {
            System.out.println(99);
        } else {
            int cnt = 99;
            for (int i = 111; i <= N; i++) {
                int a = i / 100;
                int b = (i / 10) % 10;
                int c = (i % 10)  % 10;
                int tmp1 = b-a;
                int tmp2 = c-b;
                if (tmp1 == tmp2) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}
