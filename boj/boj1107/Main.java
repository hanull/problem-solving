package boj.boj1107;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int channel = sc.nextInt();
        int brokenCnt = sc.nextInt();
        boolean[] button = new boolean[10];

        for (int i = 0; i < brokenCnt; i++) {
            int num = sc.nextInt();
            button[num] = true; // 고장난 버튼
        }
        // +, - 만 사용해서 이동하는 경우
        // 버튼으로 num 을 만들고, +, - 로 이동하는 경우
        int min = Math.min(Math.abs(channel - 100), move(channel, button));
        System.out.println(min);
    }

    private static int move(int channel, boolean[] button) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= 999999; i++) {
            if (isPossible(i, button)) {
                int len = String.valueOf(i).length();
                int total = len + Math.abs(channel - i);
                if (total < res) res = total;
            }
        }
        return res;
    }

    private static boolean isPossible(int num, boolean[] button) {
        String tmp = String.valueOf(num);
        for (int i = 0; i < tmp.length(); i++) {
            if (button[tmp.charAt(i) - '0'])
                return false;
        }
        return true;
    }
}
