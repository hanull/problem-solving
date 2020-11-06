package boj.boj1543;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String docuemnt = br.readLine();
        String word = br.readLine();
        int len = word.length();
        int cnt = 0;
        for (int i = 0; i < docuemnt.length() - len + 1; i++) {
            if (docuemnt.substring(i).startsWith(word)) {
                cnt++;
                i += len - 1;
            }
        }
        System.out.println(cnt);
    }
}
