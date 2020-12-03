package boj.boj6588;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static final int max = 1000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[] checkPrime = new boolean[max + 1];
        Arrays.fill(checkPrime, true);
        checkPrime[0] = checkPrime[1] = checkPrime[2] = false;
        for (int i = 2; i <= Math.sqrt(max); i++) {
            for (int j = i * i; j <= max; j += i) {
                if (checkPrime[j]) checkPrime[j] = false;
            }
        }
        int n = 0;
        while ((n = sc.nextInt()) != 0) {
            boolean flag = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 3; i <= n / 2; i += 2) {
                int j = n - i;
                if (!checkPrime[i] || !checkPrime[j]) continue;
                sb.append(n + " = ");
                sb.append(i + " + ");
                sb.append(j);
                flag = true;
                break;
            }
            if (!flag) sb.append("Goldbach's conjecture is wrong.");
            System.out.println(sb.toString());
        }
    }
}
