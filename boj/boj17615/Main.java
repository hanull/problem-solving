package boj.boj17615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] balls = br.readLine().toCharArray();

        int red = 0;
        int blue = 0;
        for (char ball : balls) {
            if (ball == 'R') {
                red++;
            } else {
                blue++;
            }
        }

        int answer = Integer.MAX_VALUE;
        // 빨간색 왼쪽으로
        int index = 0;
        int count = 0;
        while (index < N && balls[index++] == 'R') {
            count++;
        }
        answer = Math.min(answer, red - count);

        // 빨간색 오른쪽으로
        index = N - 1;
        count = 0;
        while (index >= 0 && balls[index--] == 'R') {
            count++;
        }
        answer = Math.min(answer, red - count);

        // 파란색 왼쪽으로
        index = 0;
        count = 0;
        while (index < N && balls[index++] == 'B') {
            count++;
        }
        answer = Math.min(answer, blue - count);

        // 파란색 오른쪽으로
        index = N - 1;
        count = 0;
        while (index >= 0 && balls[index--] == 'B') {
            count++;
        }
        answer = Math.min(answer, blue - count);

        System.out.println(answer);
    }
}
