package boj.boj9084;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, M;
    static int[] coin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        while (T-- > 0) {
            N = stoi(br.readLine());
            coin = new int[N + 1];

        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
