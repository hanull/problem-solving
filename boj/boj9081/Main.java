package boj.boj9081;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String word = br.readLine();
            System.out.println(findNextWord(word.toCharArray()));
        }
    }

    private static String findNextWord(char[] word) {
        int i = word.length - 1;

        while (i > 0 && word[i - 1] >= word[i]) i--;

        if (i == 0) return new String(word);

        int j = word.length - 1;
        while (word[i - 1] >= word[j]) j--;

        swap(i - 1, j, word);

        j = word.length - 1;
        while (i < j) {
            swap(i++, j--, word);
        }
        return new String(word);
    }

    static void swap(int i, int j, char[] word) {
        char temp = word[i];
        word[i] = word[j];
        word[j] = temp;
    }
}
