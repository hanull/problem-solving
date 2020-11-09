package boj.boj1213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringBuilder back = new StringBuilder();
        StringBuilder res = new StringBuilder();
        int[] alpa = new int[26];

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            alpa[ch - 'A'] += 1;
        }
        int midCnt = 0;
        boolean flag = true;
        for (int i = 0; i < 26; i++) {
            if (alpa[i] % 2 != 0) midCnt++;
        }
        if (midCnt > 1) flag = false;
        String mid = "";
        if (flag) {
            for (int i = 0; i < 26; i++) {
                int cnt = alpa[i];
                if (cnt == 0) continue;
                char ch = (char) (i + 'A');
                String str = String.valueOf(ch);
                if (cnt % 2 == 0) {
                    for (int j = 0; j < cnt / 2; j++) {
                        back.append(str);
                        res.append(str);
                    }
                } else {
                    for (int j = 0; j < cnt / 2; j++) {
                        back.append(str);
                        res.append(str);
                    }
                    mid = str;
                }
            }
            res.append(mid);
            res.append(back.reverse());
            System.out.println(res.toString());
        } else {
            System.out.println("I'm Sorry Hansoo");
        }
    }
}
