package boj.boj2023;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static int N;
    static int[] startArr = {2, 3, 5, 7};
    static HashSet<Integer> nonPrime = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < 4; i++) {
            findMagicNumber(1, startArr[i]);
        }
    }

    static void findMagicNumber(int cnt, int num) {
        if (cnt == N) {
            System.out.println(num);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            boolean flag = true;
            int tmp = num * 10 + i;
            if (nonPrime.contains(tmp)) continue;
            for (int n = 2; n < tmp; n++) {
                if (tmp % n == 0) {
                    nonPrime.add(tmp);
                    flag = false;
                    break;
                }
            }
            if (flag) findMagicNumber(cnt + 1, tmp);
        }
    }
}
