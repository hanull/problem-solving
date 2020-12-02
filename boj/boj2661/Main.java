package boj.boj2661;

import java.util.Scanner;

public class Main {

    static int N;
    static boolean flag = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        if (N == 1) {
            System.out.println(1);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(1);
            dfs(1, sb);
        }
    }

    private static void dfs(int cnt, StringBuilder sb) {
        if (cnt == N) {
            System.out.println(sb.toString());
            flag = true;
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (flag) return;
            if (sb.charAt(sb.length() - 1) - '0' == i) continue;
            if (!isPossible(sb, i)) continue;
            sb.append(i);
            dfs(cnt + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static boolean isPossible(StringBuilder sb, int num) {
        StringBuilder tmp = new StringBuilder(sb);
        tmp.append(num);
        int len = tmp.length();
        int mid = len / 2;
        int start = len - 1;
        int end = len;
        for (int i = 1; i <= mid; i++) {
            String left = tmp.substring(start - i, end - i);
            String right = tmp.substring(start, end);
            if (left.equals(right)) return false;
            start--;
        }
        return true;
    }
}
