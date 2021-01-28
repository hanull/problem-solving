package boj.boj1120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] A = st.nextToken().toCharArray();
        char[] B = st.nextToken().toCharArray();
        int res = A.length;

        int gap = B.length - A.length;
        for (int p = 0; p <= gap; p++) {
            int cnt = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i] != B[i + p]) cnt++;
            }
            res = Math.min(res, cnt);
        }
        System.out.println(res);
    }
}
