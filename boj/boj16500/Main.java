package boj.boj16500;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static String targetString;
    static int N;
    static boolean[] dp;
    static List<String>[] includeList = new ArrayList[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        targetString = br.readLine();
        dp = new boolean[targetString.length() + 1];
        dp[targetString.length()] = true;
        N = stoi(br.readLine());
        String[] splitString = new String[N];
        for (int i = 0; i < 26; i++) includeList[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            splitString[i] = br.readLine();
            includeList[splitString[i].charAt(splitString[i].length() - 1) - 'a'].add(splitString[i]);
        }
        checkString();
        System.out.println(dp[0] ? 1 : 0);
    }

    private static void checkString() {
        for (int i = targetString.length() - 1; i >= 0; i--) {
            if (!dp[i + 1]) continue;

            int ch = targetString.charAt(i) - 'a';
            for (String str : includeList[ch]) {
                int start = i - str.length() + 1;
                if (start < 0) continue;

                String target = targetString.substring(start, i + 1);
                if (str.equals(target)) {
                    dp[start] = true;
                }
            }
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
