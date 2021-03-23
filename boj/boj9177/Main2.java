package boj.boj9177;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static char[] firstWord, secondWord, targetWord;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            firstWord = st.nextToken().toCharArray();
            secondWord = st.nextToken().toCharArray();
            targetWord = st.nextToken().toCharArray();
            flag = false;

            result.append("Data set ").append(tc).append(": ");
            if (isSameAlphabetCount()) {
                checkAlphabet(0, 0, 0);
                result.append(flag ? "yes" : "no");
            } else {
                result.append("no");
            }
            result.append("\n");
        }
        System.out.print(result);
    }

    static void checkAlphabet(int firstWordIndex, int secondWordIndex, int targetWordIndex) {
        if (flag) return;

        if (targetWordIndex == targetWord.length) {
            flag = true;
            return;
        }

        if (firstWordIndex < firstWord.length && firstWord[firstWordIndex] == targetWord[targetWordIndex]) {
            checkAlphabet(firstWordIndex + 1, secondWordIndex, targetWordIndex + 1);
        }

        if (secondWordIndex < secondWord.length && secondWord[secondWordIndex] == targetWord[targetWordIndex]) {
            checkAlphabet(firstWordIndex, secondWordIndex + 1, targetWordIndex + 1);
        }
    }

    static boolean isSameAlphabetCount() {
        int[] operand = new int[26];
        int[] target = new int[26];

        for (char ch : firstWord) {
            if (ch >= 'A' && ch <= 'Z') {
                operand[ch - 'A']++;
            } else {
                operand[ch - 'a']++;
            }
        }
        for (char ch : secondWord) {
            if (ch >= 'A' && ch <= 'Z') {
                operand[ch - 'A']++;
            } else {
                operand[ch - 'a']++;
            }
        }
        for (char ch : targetWord) {
            if (ch >= 'A' && ch <= 'Z') {
                target[ch - 'A']++;
            } else {
                target[ch - 'a']++;
            }
        }

        for (int index = 0; index < 26; index++) {
            if (target[index] - operand[index] != 0) return false;
        }
        return true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
