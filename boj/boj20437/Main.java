package boj.boj20437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        while (T-- > 0) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int[] alpha = new int[26];
            char[] word = br.readLine().toCharArray();
            int K = stoi(br.readLine());
            for (char ch : word) {
                alpha[ch - 'a']++;
            }
            for (int i = 0; i < word.length; i++) {
                int targetCharacter = word[i];
                if (alpha[targetCharacter - 'a'] < K) continue;
                int count = 0;
                for (int j = i; j < word.length; j++) {
                    if (targetCharacter == word[j]) {
                        count++;
                    }
                    if (count == K) {
                        min = Math.min(min, j - i + 1);
                        max = Math.max(max, j - i + 1);
                        break;
                    }
                }
            }
            if (min == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(min + " " + max);
            }
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
