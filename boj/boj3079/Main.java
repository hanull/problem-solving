package boj.boj3079;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        int[] times = new int[N];
        for (int i = 0; i < N; i++) {
            times[i] = stoi(br.readLine());
        }
        Arrays.sort(times);
        long left = 1;
        long right = (long) M * times[N - 1];
        long answer = right;
        while (left <= right) {
            long mid = (left + right) / 2;
            long total = 0;
            for (int time : times) {
                total += mid / time;
            }
            if (total >= M) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
