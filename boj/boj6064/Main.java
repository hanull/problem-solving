package boj.boj6064;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = stoi(st.nextToken());
            int N = stoi(st.nextToken());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            int a = 1;
            int b = 1;
            int day = 1;
            boolean flag = false;
            while (a != M || b != N) {
                if (a > M) a = 1;
                if (b > N) b = 1;
                if (a == x && b == y) {
                    flag = true;
                    break;
                }
                a++; b++; day++;
            }
            if (flag) {
                System.out.println(day);
            } else {
                System.out.println("-1");
            }
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
