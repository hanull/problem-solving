package boj.boj17266;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int M = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] streetlight = new int[M];
        for (int i = 0; i < M; i++) {
            streetlight[i] = stoi(st.nextToken());
        }
        int left = 0;
        int right = N;
        int answer = N;
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean flag = true;
            if (streetlight[0] - mid > 0) flag = false;
            if (streetlight[M - 1] + mid < N) flag = false;

            for (int i = 1; i < M - 1; i++) {
                if (streetlight[i] - mid > streetlight[i - 1] + mid) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                right = mid - 1;
                answer = Math.min(answer, mid);
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
