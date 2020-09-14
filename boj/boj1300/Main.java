package boj.boj1300;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int left = 1;
        int right = K;
        int res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            long cnt = 0;
            for (int i = 1; i <= N; i++) {
                cnt += Math.min(mid / i, N);
            }
            if (cnt < K) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid - 1;
            }
        }
        System.out.println(res);
    }
}
