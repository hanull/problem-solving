package boj.boj18113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        int[] arr = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(br.readLine());
            if (arr[i] >= 2 * K) {
                arr[i] -= 2 * K;
            } else if (arr[i] < K) {
                arr[i] = 0;
            } else {
                arr[i] -= K;
            }
            max = Math.max(max, arr[i]);
        }
        int left = 1;
        int right = max;
        int answer = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int total = 0;
            for (int i = 0; i < N; i++) {
                total += arr[i] / mid;
            }
            if (total >= M) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
