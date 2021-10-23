package boj.boj16564;

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
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(br.readLine());
        }
        int left = 1;
        int right = 1000000000;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            long sum = 0;
            for (int i = 0; i < N; i++) {
                if (mid - arr[i] > 0) {
                    sum += mid - arr[i];
                }
            }
            if (sum > K) {
                right = mid - 1;
            } else {
                left = mid + 1;
                answer = Math.max(answer, mid);
            }
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
