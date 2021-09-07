package boj.boj14405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(isPossible(input) ? "YES" : "NO");
    }

    private static boolean isPossible(String input) {
        char[] tmp = input.toCharArray();
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == 'p') {
                if (!input.startsWith("pi", i)) return false;
                i += 1;
            } else if (tmp[i] == 'k') {
                if (!input.startsWith("ka", i)) return false;
                i += 1;
            } else if (tmp[i] == 'c') {
                if (!input.startsWith("chu", i)) return false;
                i += 2;
            } else {
                return false;
            }
        }
        return true;
    }
}
