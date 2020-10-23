package Goorm.test1;

import java.io.*;
import java.util.StringTokenizer;

class Main {

    static int N, K;
    static int[] arr;
    static int cur;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(st.nextToken());
            if (arr[i] == 1) cur = i;
        }
        solve();
        System.out.println(min);
    }

    private static void solve() {
        int start = cur - K + 1;
        int end = N - K;
        while (true) {
            if (start > end) break;
            if (start >= 0) {
                int left = 0;
                int tmp = 0;
                if (start > 0) {
                    tmp = start % (K - 1) > 0 ? 1 : 0;
                    left = start / (K - 1) + tmp;
                }
                int endPoint = start + K - 1;
                tmp = (N - 1 - endPoint) % (K - 1) > 0 ? 1 : 0;
                int right = (N - 1 - endPoint) / (K - 1) + tmp;
                int total = left + right + 1;
                if (total < min) min = total;
            }
            start++;
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}