package boj.boj12015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
8
10 20 30 5 10 20 30 40
 */

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] numberArray = new int[N];
        int[] LIS = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numberArray[i] = stoi(st.nextToken());
        }

        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            if (i == 0 || numberArray[i] > LIS[maxLength - 1]) {
                LIS[maxLength++] = numberArray[i];
                continue;
            }
            int left = 0;
            int right = maxLength - 1;
            int index = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (numberArray[i] <= LIS[mid]) {
                    index = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            LIS[index] = numberArray[i];
            if (index == maxLength) maxLength++;
        }
        System.out.println(maxLength);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
