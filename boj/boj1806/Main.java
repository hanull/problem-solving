package boj.boj1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        int right = 0;
        int hap = arr[left];
        int res = Integer.MAX_VALUE;
        while (true) {
            if (hap >= s) {
                res = Math.min(res, right - left + 1);
                hap -= arr[left];
                left++;
            } else if (right == n - 1) {
                break;
            } else {
                right++;
                hap += arr[right];
            }
        }
        if (res == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(res);
        }
    }
}
