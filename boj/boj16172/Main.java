package boj.boj16172;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String target = br.readLine();
        System.out.println(isContain(input.replaceAll("[0123456789]", ""), target) ? 1 : 0);
    }

    private static boolean isContain(String input, String target) {
        for (int i = 0; i <= input.length() - target.length(); i++) {
            if (input.startsWith(target, i)) return true;
        }
        return false;
    }

}
