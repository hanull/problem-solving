package boj.boj2104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        while (T-- > 0) {
            N = stoi(br.readLine());
            arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = stoi(st.nextToken());
            }
            System.out.println(divideConquer(0, N - 1));

        }

    }

    static int divideConquer(int lo, int hi) {
        if (lo == hi) return arr[lo];

        int mid = (lo + hi) / 2;

        int tmp = 0;
        int leftPart = Integer.MIN_VALUE;
        for (int i = mid; i >= lo; i--) {
            tmp += arr[i];
            leftPart = Math.max(leftPart, tmp);
        }

        tmp = 0;
        int rightPart = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= hi; i++) {
            tmp += arr[i];
            rightPart = Math.max(rightPart, tmp);
        }

        int left = divideConquer(lo, mid);
        int right = divideConquer(mid + 1, hi);
        int maxSinglePart = Math.max(left, right);

        return Math.max(maxSinglePart, leftPart + rightPart);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
