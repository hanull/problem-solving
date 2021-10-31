package boj.boj11478;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> used = new HashSet<>();
        String input = br.readLine();
        for (int len = 1; len <= input.length(); len++) {
            for (int i = 0; i <= input.length() - len; i++) {
                String newString = input.substring(i, i + len);
                if (!used.contains(newString)) {
                    used.add(newString);
                }
            }
        }
        System.out.println(used.size());
    }

}
