package boj.boj9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        String targetString = br.readLine();
        String answer = explosion(input, targetString);
        System.out.println(answer.length() == 0 ? "FRULA" : answer);
    }

    private static String explosion(char[] input, String targetString) {
        char[] answer = new char[input.length];
        int index = 0;
        for (int i = 0; i < input.length; i++) {
            answer[index++] = input[i];
            if (index >= targetString.length() && isContains(answer, targetString, index))  {
                index -= targetString.length();
            }
        }
        return String.valueOf(answer, 0, index);
    }

    private static boolean isContains(char[] answer, String targetString, int index) {
        int start = index - targetString.length();
        int idx = 0;
        for (int i = start; i < index; i++) {
            if (answer[i] != targetString.charAt(idx++)) return false;
        }
        return true;
    }
}
