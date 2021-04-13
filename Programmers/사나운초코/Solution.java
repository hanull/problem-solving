package Programmers.사나운초코;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static int N, K;
    static int[] puppiesPoint;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        puppiesPoint = new int[N];
        for (int i = 0; i < N; i++) {
            puppiesPoint[i] = sc.nextInt();
        }
        Arrays.sort(puppiesPoint);
        System.out.println(solve());
    }

    static int solve() {
        int result = 0;

        int left = 1;   // 최소 간격
        int right = puppiesPoint[N - 1] - puppiesPoint[0];  // 최대 간격
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 1;
            int previous = puppiesPoint[0];
            for (int i = 1; i < N; i++) {
                int dist = puppiesPoint[i] - previous;
                if (dist >= mid) {
                    count++;
                    previous = puppiesPoint[i];
                }
            }
            if (count >= K) {
                left = mid + 1;
                result = mid;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}
