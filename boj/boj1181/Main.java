package boj.boj1181;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        HashSet<String> checkString = new HashSet<>();
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (!checkString.contains(str)) {
                checkString.add(str);
                strings.add(str);
            }
        }
        Collections.sort(strings, (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        });
        StringBuilder answer = new StringBuilder();
        for (String string : strings) {
            answer.append(string).append("\n");
        }
        answer.deleteCharAt(answer.length() - 1);
        System.out.print(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
