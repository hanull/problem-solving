package boj.boj2428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sizes = new int[N];
        for (int i=0; i<N; i++) {
            sizes[i] = stoi(st.nextToken());
        }
        Arrays.sort(sizes);
        long answer = 0;
        for (int i=0; i<N - 1; i++) {
            int left = i;
            int right = N;
            while (left < right) {
                int mid = (left + right) / 2;
                if (sizes[i] >= sizes[mid] * 0.9) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            answer += right - 1 - i;
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
