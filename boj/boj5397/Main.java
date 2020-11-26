package boj.boj5397;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = stoi(br.readLine());
        for (int i = 0; i < N; i++) {
            LinkedList<Character> list = new LinkedList<>();
            ListIterator<Character> listIterator = list.listIterator();
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                char tmp = input.charAt(j);
                if (tmp == '<') {
                    if (listIterator.hasPrevious()) listIterator.previous();
                } else if (tmp == '>') {
                    if (listIterator.hasNext()) listIterator.next();
                } else if (tmp == '-') {
                    if (listIterator.hasPrevious()) {
                        listIterator.previous();
                        listIterator.remove();
                    }
                } else {
                    listIterator.add(tmp);
                }
            }
            for (char ch : list) {
                bw.write(ch);
            }
            bw.write("\n");
        }
        bw.close();
        br.close();
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
