package boj.boj2512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxBudget = Integer.parseInt(br.readLine());
        int[] budget = new int[N];
        int left = 0;
        int right = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            budget[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, budget[i]);
        }
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int total = 0;
            for (int i = 0; i < N; i++) {
                total += Math.min(budget[i], mid);
            }
            if (total <= maxBudget) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }
}
