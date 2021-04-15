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
        N = stoi(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(st.nextToken());
        }

        System.out.println(divideConquer(0, N-1, findMin(0, N - 1)));
    }

    static int divideConquer(int lo, int hi, int mul) {
        if (lo == hi) return arr[lo];

        int mid = (lo + hi) / 2;
//
//        int tmp = 0;
//        int min = Integer.MAX_VALUE;
//        int leftPart = Integer.MIN_VALUE;
//        for (int i = mid; i >= lo; i--) {
//            min = Math.min(min, arr[i]);
//            tmp +=
//        }
//
//
//        int left = divideConquer(lo, mid, findMin(lo, mid));
//        int right = divideConquer(mid + 1, hi, findMin(mid + 1, hi));
//        int maxSinglePart = Math.max(left, right) * mul;
//
//        return Math.max(maxSinglePart, midPart);
        return 0;
    }

    static int findMin(int lo, int hi) {
        int min = Integer.MAX_VALUE;
        for (int i = lo; i <= hi; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
