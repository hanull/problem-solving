package SWEA.금고털기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
5
FDSA
JATY
AAAA
ZZZZ
SSSS
SSSS
ZXKC
QWWW
YTUY
ZIQZ
 */
public class Solution {

    static int[][] time = {{1, 1}, {3, 2}, {5, 4}, {7, 6}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            char[] input = br.readLine().toCharArray();
            char[] target = br.readLine().toCharArray();
            int totalTime = 0;
            for (int i = 0; i < 4; i++) {
                int right = findAlpha(input[i], target[i], 1) * time[i][1];
                int left = findAlpha(input[i], target[i], 0) * time[i][0];
                totalTime += Math.min(right, left);
            }
            answer.append("#").append(tc).append(" ").append(totalTime).append("\n");
        }
        System.out.print(answer);
    }

    private static int findAlpha(char current, char target, int flag) {
        int total = 0;
        if (flag == 0) {
            for (char i = current; i >= 'A'; i--) {
                if (i == target) return total;
                total++;
            }
            for (char i = 'Z'; i >= 'A'; i--) {
                if (i == target) return total;
                total++;
            }
        } else {
            for (char i = current; i <= 'Z'; i++) {
                if (i == target) return total;
                total++;
            }
            for (char i = 'A'; i <= 'Z'; i++) {
                if (i == target) return total;
                total++;
            }
        }
        return -1;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
