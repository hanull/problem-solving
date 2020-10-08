package boj.boj2455;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = stoi(st.nextToken()) + stoi(st.nextToken());
        int max = total;
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            int minus = stoi(st.nextToken());
            int add = stoi(st.nextToken());
            total += (add - minus);
            if (max < total) max = total;
        }
        System.out.println(max);
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
