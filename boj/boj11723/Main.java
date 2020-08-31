package boj.boj11723;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String operator;
    static int num;
    static HashSet<Integer> hm = new HashSet<>();

    public static void main(String[] args) throws IOException {
        int M = stoi(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            operator = st.nextToken();
            if ((operator.equals("all") || operator.equals("empty"))) {
                num = 0;
            } else {
                num = stoi(st.nextToken());
            }
            cal(operator, hm, num);
        }
        br.close();
        bw.close();
    }

    private static void cal(String op, HashSet<Integer> hm, int num) throws IOException {
        switch (op) {
            case "add":
                hm.add(num);
                break;
            case "remove":
                if (hm.contains(num)) {
                    hm.remove(num);
                }
                break;
            case "check":
                if (hm.contains(num)) {
                    bw.write(1 + "\n");
                } else {
                    bw.write(0 + "\n");
                }
                break;
            case "toggle":
                if (hm.contains(num)) {
                    hm.remove(num);
                } else {
                    hm.add(num);
                }
                break;
            case "all":
                hm.clear();
                for (int i = 1; i <= 20; i++) {
                    hm.add(i);
                }
                break;
            case "empty":
                hm.clear();
                break;
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
