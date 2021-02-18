package boj.boj1074;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();

        int len = (int) Math.pow(2, N);
        int x = 0;
        int y = 0;
        int totalCount = 0;

        while (len > 1) {
            len /= 2;

            // 1
            if (r < x + len && c < y + len) continue;

            // 2
            else if (r < x + len && c >= y + len) {
                y += len;
                totalCount += Math.pow(len, 2);
            }

            // 3
            else if (r >= x + len && c < y + len) {
                x += len;
                totalCount += Math.pow(len, 2) * 2;
            }
            // 4
            else {
                x += len;
                y += len;
                totalCount += Math.pow(len, 2) * 3;
            }

        }
        System.out.println(totalCount);
    }

}
