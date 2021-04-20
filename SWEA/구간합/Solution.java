package SWEA.구간합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static long[] countArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            countArray = new long[10];
            long A = stol(st.nextToken());
            long B = stol(st.nextToken());
            long point = 1;

            while (A <= B) {
                while (B % 10 != 9 && A <= B) {
                    calculate(B, point);
                    B--;
                }

                if (B < A) break;

                while (A % 10 != 0 && A <= B) {
                    calculate(A, point);
                    A++;
                }

                A /= 10;
                B /= 10;
                for (int i = 0; i < 10; i++) {
                    countArray[i] += (B - A + 1) * point;
                }
                point *= 10;
            }

            result.append("#").append(tc).append(" ").append(getTotal()).append("\n");
        }
        System.out.print(result);
    }

    private static long getTotal() {
        long result = 0;
        for (int i = 0; i < 10; i++) {
            result += countArray[i] * i;
        }
        return result;
    }

    private static void calculate(long number, long point) {
        while (number > 0) {
            countArray[Long.valueOf(number % 10).intValue()] += point;
            number /= 10;
        }
    }

    private static long stol(String input) {
        return Long.parseLong(input);
    }

}
