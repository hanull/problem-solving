package SWEA.사칙연산유효성검사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= 10; tc++) {
            N = stoi(br.readLine());
            int res = 1;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                if (st.countTokens() > 2) {
                    st.nextToken();
                    char ch = st.nextToken().charAt(0);
                    if (!(ch == '+' || ch == '-' || ch == '*' || ch == '/')) {
                        res = 0;
                    }
                } else {
                    st.nextToken();
                    char ch = st.nextToken().charAt(0);
                    if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                        res = 0;
                    }
                }
            }
            System.out.println("#" + tc + " " + res);

        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
