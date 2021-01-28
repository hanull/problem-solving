package boj.boj2941;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        printResult(getLengthOfKroatiaWord(makeInput()));
    }

    private static int getLengthOfKroatiaWord(String word) {
        String[] delim = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        for (String d : delim) {
            if (word.contains(d)) {
                word = word.replace(d, "#");
            }
        }
        return word.length(); // @return: the length Of Kroatia Word
    }

    private static String makeInput() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    private static void printResult(int lengthOfKroatiaWord) {
        System.out.println(lengthOfKroatiaWord);
    }
}
