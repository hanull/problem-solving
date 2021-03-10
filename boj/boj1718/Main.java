package boj.boj1718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] plainText = br.readLine().toCharArray();
        char[] cipherText = br.readLine().toCharArray();
        char[] res = new char[plainText.length];

        int j = 0;
        int len = cipherText.length;
        for (int i = 0; i < plainText.length; i++) {
            if (j == len) j = 0;
            int sub = plainText[i] - cipherText[j++] - 1;
            if (sub < 0) {
                sub = 'z' - (cipherText[j - 1] - plainText[i] + 'a');
            }
            if (plainText[i] == ' ') {
                res[i] = ' ';
            } else {
                res[i] = (char) (sub + 'a');
            }
        }
        System.out.println(new String(res));

    }
}
