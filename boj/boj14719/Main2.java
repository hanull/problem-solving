package boj.boj14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = stoi(st.nextToken());
        int W = stoi(st.nextToken());
        int[] block = new int[W];
        int max = 0;
        int mid = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            block[i] = stoi(st.nextToken());
            if (block[i] > max) {
                max = block[i];
                mid = i;
            }
        }

        int total = 0;
        int temp = 0;
        for (int i = 0; i < mid; i++) {
            if (block[i] >= temp) {
                temp = block[i];
            } else {
                total += temp - block[i];
            }
        }

        temp = 0;
        for (int i = W - 1; i > mid; i--) {
            if (block[i] >= temp) {
                temp = block[i];
            } else {
                total += temp - block[i];
            }
        }
        System.out.println(total);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
