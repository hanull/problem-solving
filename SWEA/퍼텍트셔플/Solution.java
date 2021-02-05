package SWEA.퍼텍트셔플;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T, N;
    static String[] input;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        T = stoi(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = stoi(br.readLine());
            input = new String[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                input[i] = st.nextToken();
            }

            sb = new StringBuilder("#" + tc + " ");
            int left = 0;
            int mid = N / 2;
            int right = N % 2 == 0 ? mid : mid + 1;

            for (int i = 0; i < mid; i++) {
                sb.append(input[left + i] + " " + input[right + i] + " ");
            }
            if (N % 2 != 0) sb.append(input[mid]);
            System.out.println(sb);
        }

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
