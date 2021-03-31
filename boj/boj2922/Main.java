package boj.boj2922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/*
_A__E_C_D_I_G_O__

1905596317800
 */

public class Main {

    static String input;
    static int inputLength;
    static long totalCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        inputLength = input.length();

        findAllAlphabet(0, 0, 0, false, 1);

        System.out.println(totalCount);
    }

    static void findAllAlphabet(int index, int vowlesCount, int consonantsCount, boolean flag, long count) {
        if (index == inputLength) {
            if (flag) totalCount += count;
            return;
        }

        char ch = input.charAt(index);

        if (ch == '_') {
            if (vowlesCount == 2) {
                findAllAlphabet(index + 1, 0, consonantsCount + 1, flag, count * 20);
                findAllAlphabet(index + 1, 0, consonantsCount + 1, true, count);
            }
            else if (consonantsCount == 2) {
                findAllAlphabet(index + 1, vowlesCount + 1, 0, flag, count * 5);
            }
            else {
                findAllAlphabet(index + 1, 0, consonantsCount + 1, flag, count * 20);
                findAllAlphabet(index + 1, 0, consonantsCount + 1, true, count);
                findAllAlphabet(index + 1, vowlesCount + 1, 0, flag, count * 5);
            }

        } else if (isVowel(ch)) {
            if (vowlesCount == 2) return;
            findAllAlphabet(index + 1, vowlesCount + 1, 0, flag, count);
        } else {
            if (consonantsCount == 2) return;
            findAllAlphabet(index + 1, 0, consonantsCount + 1, ch == 'L' ? true : flag, count);
        }

    }

    static boolean isVowel(char ch) {
        char[] vowels = "AEIOU".toCharArray();
        for (char check : vowels) {
            if (ch == check) return true;
        }
        return false;
    }

}
