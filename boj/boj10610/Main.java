package boj.boj10610;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.next();
        if (isPossible(N)) {
            String res = sortNum(N);
            System.out.println(res);
        } else {
            System.out.println(-1);
        }
    }

    private static String sortNum(String input) {
        char[] num = input.toCharArray();

        Arrays.sort(num);
        StringBuilder sb = new StringBuilder(new String(num));
        return sb.reverse().toString();
    }

    private static boolean isPossible(String input) {
        char[] num = input.toCharArray();
        int hap = 0;

        if (!input.contains("0")) {
            return false;
        }
        for (int i = 0; i < num.length; i++) {
            hap += num[i] - '0';
        }
        if (hap % 3 != 0) {
            return false;
        }
        return true;
    }
}
