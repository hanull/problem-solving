package boj.boj17413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        char[] input = br.readLine().toCharArray();
        boolean flag = false;
        StringBuilder tmp = new StringBuilder();
        for (char ch : input) {
            if (ch == '<') {
                answer.append(tmp.reverse());
                tmp = new StringBuilder();
                flag = true;
                answer.append(ch);
            } else if (ch == '>') {
                flag = false;
                answer.append(ch);
            } else if (flag) {
                answer.append(ch);
            } else if (ch == ' ') {
                answer.append(tmp.reverse()).append(ch);
                tmp = new StringBuilder();
            } else {
                tmp.append(ch);
            }
        }
        answer.append(tmp.reverse());
        System.out.print(answer);
    }
}
