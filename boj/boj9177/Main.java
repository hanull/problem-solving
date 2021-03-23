package boj.boj9177;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[] firstWord, secondWord, targetWord;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = stoi(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            firstWord = st.nextToken().toCharArray();
            secondWord = st.nextToken().toCharArray();
            targetWord = st.nextToken().toCharArray();
            flag = false;

            sb.append("Data set ").append(i).append(": ");
            if (isPossible()) {
                dfs(0, 0, 0);
                sb.append(flag ? "yes" : "no").append("\n");
            } else {
                sb.append("no").append("\n");
            }
        }
        System.out.print(sb);

    }

    static boolean isPossible() {
        int[] operandAlphabetCount = new int[26];
        int[] targetAlphabetCount = new int[26];

        for (char ch : firstWord) {
            if (ch >= 'A' && ch <= 'Z') {
                operandAlphabetCount[ch - 'A']++;
            } else {
                operandAlphabetCount[ch - 'a']++;
            }
        }
        for (char ch : secondWord) {
            if (ch >= 'A' && ch <= 'Z') {
                operandAlphabetCount[ch - 'A']++;
            } else {
                operandAlphabetCount[ch - 'a']++;
            }
        }
        for (char ch : targetWord) {
            if (ch >= 'A' && ch <= 'Z') {
                targetAlphabetCount[ch - 'A']++;
            } else {
                targetAlphabetCount[ch - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (operandAlphabetCount[i] - targetAlphabetCount[i] != 0) return false;
        }
        return true;
    }

    static void dfs(int firstWordIndex, int secondWordIndex, int targetWordIndex) {
        if (flag) return;

        if (targetWordIndex == targetWord.length) {
            flag = true;
            return;
        }

        if (firstWordIndex < firstWord.length && firstWord[firstWordIndex] == targetWord[targetWordIndex]) {
            dfs(firstWordIndex + 1, secondWordIndex, targetWordIndex + 1);
        }
        if (secondWordIndex < secondWord.length && secondWord[secondWordIndex] == targetWord[targetWordIndex]) {
            dfs(firstWordIndex, secondWordIndex + 1, targetWordIndex + 1);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
