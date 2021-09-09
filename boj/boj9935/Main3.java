package boj.boj9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String target = br.readLine();
        System.out.println(explosion(input, target));
    }

    private static String explosion(String input, String target) {
        char[] answer = new char[input.length()];
        int index = 0;
        for (int i = 0; i < input.length(); i++) {
            answer[index++] = input.charAt(i);
            if (index >= target.length() && isSame(index, answer, target)) {
                index -= target.length();
            }
        }
        return index == 0 ? "FRULA" : String.valueOf(answer, 0, index);
    }

    private static boolean isSame(int index, char[] input, String target) {
        int j = 0;
        for (int i = index - target.length(); i < index; i++) {
            if (input[i] != target.charAt(j++)) return false;
        }
        return true;
    }
}
