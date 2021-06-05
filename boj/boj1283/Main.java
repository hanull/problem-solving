package boj.boj1283;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    static int N;
    static HashSet<Character> keySet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        String[] answer = new String[N];
        Arrays.fill(answer, "");
        for (int index = 0; index < N; index++) {
            String[] words = br.readLine().split(" ");
            boolean flag = false;
            for (int i = 0; i < words.length; i++) {
                if (!keySet.contains(words[i].charAt(0)) && !flag) {
                    char ch = words[i].charAt(0);
                    flag = true;
                    keySet.add(ch);
                    if (ch >= 'A' && ch <= 'Z') keySet.add(String.valueOf(ch).toLowerCase().charAt(0));
                    else keySet.add(String.valueOf(ch).toUpperCase().charAt(0));
                    answer[index] += "[" + words[i].charAt(0) + "]" + words[i].substring(1);
                } else {
                    answer[index] += (words[i]);
                }
                if (i < words.length - 1) answer[index] += " ";
            }
            if (flag) continue;
            answer[index] = "";
            for (int i = 0; i < words.length; i++) {
                for (char ch : words[i].toCharArray()) {
                    if (!keySet.contains(ch) && !flag) {
                        flag = true;
                        keySet.add(ch);
                        if (ch >= 'A' && ch <= 'Z') keySet.add(String.valueOf(ch).toLowerCase().charAt(0));
                        else keySet.add(String.valueOf(ch).toUpperCase().charAt(0));
                        answer[index] += ("[" + ch + "]");
                    } else {
                        answer[index] += ch;
                    }
                }
                if (i < words.length - 1) answer[index] += " ";
            }
        }
        for (String str : answer) {
            System.out.println(str);
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
