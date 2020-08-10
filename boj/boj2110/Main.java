package boj.boj2110;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static int solve(int[] arr, int maxCount) {
        int left = 1;   // 최소 간격
        int right = arr[arr.length - 1] - arr[0]; // 최대 간격
        int res = 0;

        // 이분 탐색 : 최소 간격(left) ~ 최대 간격(right)
        while (left <= right) {
            int mid = (left + right) / 2;   // 기준 간격
            int cnt = 1;    // 설치한 공유기 개수
            int start = arr[0]; // 기준이 될 공유기 위치
            for (int i = 1; i < arr.length; i++) {  // mid 간격으로 공유기 설치
                int dist = arr[i] - start;
                if (dist >= mid) {
                    cnt++;
                    start = arr[i];
                }
            }
            if (cnt >= maxCount) {  // 설치한 공유기가 목표보다 많은 경우, 간격이 좁은 것이다. 간격을 넓혀야함.
                left = mid + 1;
                res = mid;
            } else {    //  반대로 간격이 넓은 경우, 간격을 좁혀야함.
                right = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        System.out.println(solve(arr, c));
    }
}
