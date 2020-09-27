package boj.boj1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] alpa = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int len = str.length();
            for (int j = 0; j < len; j++) {
                int ch = str.charAt(j) - 'A';
                alpa[ch] += Math.pow(10, (len - j - 1));
            }
        }
        Arrays.sort(alpa);
        int total = 0;
        int value = 9;
        for (int i = alpa.length - 1; i >= 0; i--) {
            if (alpa[i] == 0) {
                break;
            }
            total += alpa[i] * value;
            value--;
        }
        System.out.println(total);
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
