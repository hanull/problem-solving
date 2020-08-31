package boj.boj16500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String inputStr;
    static int N;
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputStr = br.readLine();
        N = stoi(br.readLine());

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }
        System.out.println(isPossible());
    }

    private static int isPossible() {
        int[] dp = new int[inputStr.length()];
        for (int i = 0; i < words.length; i++) {

        }
        return 0;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
