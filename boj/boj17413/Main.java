package boj.boj17413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringBuilder result = new StringBuilder();
        StringBuilder tmp;

        int i = 0;
        int len = input.length();
        while (i < len) {
            char ch = input.charAt(i);
            if (ch == '<') {
                while (ch != '>') {
                    result.append(ch);
                    i++;
                    ch = input.charAt(i);
                }
                i++;
                result.append(ch);
            } else if (ch == ' ') {
                result.append(ch);
                i++;
            } else {
                tmp = new StringBuilder();
                while (i  < len && ch != ' ' && ch != '<') {
                    tmp.append(ch);
                    i++;
                    if (i >= len) break;
                    ch = input.charAt(i);
                }
                result.append(tmp.reverse());
            }
        }
        System.out.println(result);

    }
}
