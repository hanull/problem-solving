package boj.boj2559;

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
        int[] temperature = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            temperature[i] = stoi(st.nextToken());
            temperature[i] += temperature[i - 1];
        }
        System.out.println(maxCalculateTemperature(temperature, N, K));
    }

    private static int maxCalculateTemperature(int[] temperature, int N, int K) {
        int max = Integer.MIN_VALUE;
        int i = K;
        int j = 0;
        while (i <= N) {
            int total = temperature[i] - temperature[j];
            if (total > max) max = total;
            i++;
            j++;
        }
        return max;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
