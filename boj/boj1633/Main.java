package boj.boj1633;

import java.util.Scanner;

public class Main {

    static int[] white = new int[1001];
    static int[] black = new int[1001];
    static int[][][] dp = new int[1001][16][16];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int index = 0;
        while (sc.hasNextInt()) {
            white[index] = sc.nextInt();
            black[index] = sc.nextInt();
            index++;
        }
        System.out.println(solution(0, 0, 0, index));
    }

    private static int solution(int whiteIndex, int blackIndex, int index, int N) {
        if (index == N) return 0;
        if (whiteIndex == 15 && blackIndex == 15) return 0;

        if (dp[index][whiteIndex][blackIndex] != 0) return dp[index][whiteIndex][blackIndex];

        int answer = solution(whiteIndex, blackIndex, index + 1, N);

        if (whiteIndex < 15) {
            answer = Math.max(answer, solution(whiteIndex + 1, blackIndex, index + 1, N) + white[index]);
        }
        if (blackIndex < 15) {
            answer = Math.max(answer, solution(whiteIndex, blackIndex + 1, index + 1, N) + black[index]);
        }
        dp[index][whiteIndex][blackIndex] = answer;
        return answer;
    }
}
