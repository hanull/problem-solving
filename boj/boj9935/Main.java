package boj.boj9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();
        String targetString = br.readLine();
        String answer = explosion(inputString, targetString);
        System.out.println(answer.length() == 0 ? "FRULA" : answer);
    }

    private static String explosion(String inputString, String targetString) {
        char[] answer = new char[inputString.length()];
        int index = 0;
        for (int i = 0; i < inputString.length(); i++) {
            answer[index++] = inputString.charAt(i);
            if (index >= targetString.length()) {
                if (isContains(answer, index, targetString)) index -= targetString.length();
            }
        }
        return String.valueOf(answer, 0, index);
    }

    private static boolean isContains(char[] answer, int index, String targetString) {
        int targetIndex = targetString.length() - 1;
        for (int i = index - 1; i >= index - targetString.length(); i--) {
            if (answer[i] != targetString.charAt(targetIndex--)) return false;
        }
        return true;
    }
}
