package boj.boj2512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        int budget = Integer.parseInt(br.readLine());
        int left = 0;
        int right = max;
        int res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int total = getTotal(arr, mid);
            if (total <= budget) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(res);
    }

    private static int getTotal(int[] arr, int money) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += arr[i] <= money ? arr[i] : money;
        }
        return res;
    }
}
