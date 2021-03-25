package SWEA.최장증가부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            int N = stoi(br.readLine());
            int[] numberArray = new int[N];
            int[] LIS = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numberArray[i] = stoi(st.nextToken());
            }
            int maxLength = 0;
            for (int i = 0; i < N; i++) {
                LIS[i] = 1;
                for (int j = i - 1; j >= 0; j--) {
                    if (numberArray[j] < numberArray[i] && LIS[i] < LIS[j] + 1) {
                        LIS[i] = LIS[j] + 1;
                    }
                }
                maxLength = Math.max(maxLength, LIS[i]);
            }
            result.append("#").append(tc).append(" ").append(maxLength).append("\n");
        }
        System.out.print(result);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
