package boj.boj1120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        String A = input[0];
        String B = input[1];
        int cnt = B.length() - A.length();
        int res = 50;
        for (int i = 0; i <= cnt; i++) {
            int total = 0;
            for (int j = 0; j < A.length(); j++) {
                if (A.charAt(j) != B.charAt(i + j)) total++;
            }
            res = Math.min(res, total);
        }
        System.out.println(res);
    }
}