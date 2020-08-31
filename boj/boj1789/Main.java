package boj.boj1789;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long hap = 1;
        long res = 1;
        for (long i = 2L; i <= 4294967295L; i++) {
            if (hap == N) {
                System.out.println(res);
                break;
            } else if (hap > N) {
                long tmp = hap - N;
                res--;
                System.out.println(res);
                break;
            } else {
                hap += i;
                res++;
            }
        }
    }
}
