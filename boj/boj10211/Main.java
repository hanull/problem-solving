package boj.boj10211;

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

            int max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                int total = 0;
                for (int j = i; j < N; j++) {
                    total += arr[j];
                    max = Math.max(max, total);
                }
            }
            System.out.println(max);
        }

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
