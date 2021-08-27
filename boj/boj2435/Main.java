package boj.boj2435;

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
        st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = stoi(st.nextToken());
        }
        int max = 0;
        for (int i = 0; i < K; i++) {
            max += numbers[i];
        }
        int total = max;
        for (int i = K; i < N; i++) {
            total = total + numbers[i] - numbers[i - K];
            max = Math.max(max, total);
        }
        System.out.println(max);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
