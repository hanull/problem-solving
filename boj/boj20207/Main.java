package boj.boj20207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer = 0;
        int[] calendar = new int[367];
        int N = stoi(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = stoi(st.nextToken());
            int end = stoi(st.nextToken());
            for (int d = start; d <= end; d++) {
                calendar[d]++;
            }
        }
        int width = 0;
        int height = 0;
        for (int i = 1; i <= 366; i++) {
            if (calendar[i] == 0) {
                answer += width * height;
                width = 0;
                height = 0;
            } else {
                width++;
                height = Math.max(height, calendar[i]);
            }
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
