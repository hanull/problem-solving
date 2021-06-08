package boj.boj6443;

import java.io.*;
import java.util.Arrays;

public class Main {

    static char[] alpha, temp;
    static int[] alphaCount;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        while (N-- > 0) {
            alpha = br.readLine().toCharArray();
            temp = new char[alpha.length];
            alphaCount = new int[26];
            Arrays.sort(alpha);
            for (int i = 0; i < alpha.length; i++) {
                alphaCount[alpha[i] - 'a']++;
            }
            dfs(0, temp.length);
        }
        System.out.println(answer);
    }

    private static void dfs(int count, int n) {
        if (count == n) {
            answer.append(new String(temp) + "\n");
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (alphaCount[i] == 0) continue;
            alphaCount[i]--;
            temp[count] = (char) (i + 'a');
            dfs(count + 1, n);
            alphaCount[i]++;
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
