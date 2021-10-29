package boj.boj9461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        long[] arr = new long[101];
        arr[0] = arr[1] = arr[2] = 1;
        arr[3] = arr[4] = 2;
        for (int i = 5; i <= 100; i++) {
            arr[i] = arr[i - 2] + arr[i - 3];
        }
        for (int i = 0; i < T; i++) {
            int n = stoi(br.readLine()) - 1;
            System.out.println(arr[n]);
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
