package boj.boj2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        System.out.println(solve(arr));
    }

    private static String solve(int[] arr) {
        int s = 0;
        int e = arr.length - 1;
        long total = 2000000000;
        int pointA = 0;
        int pointB = 0;
        StringBuilder sb = new StringBuilder();
        while (s < e) {
            long hap = arr[s] + arr[e];
            long tmp = Math.abs(hap);
            if (tmp < total) {
                total = tmp;
                pointA = s; pointB = e;
            }
            if (hap > 0) {  // e위치의 절대값이 s위치의 절대값 보다 더 큰 경우. 절대값을 줄여야하기 때문에 e--
                e--;
            } else {
                s++;
            }
        }
        sb.append(arr[pointA] + " " + arr[pointB]);
        return sb.toString();
    }
}
