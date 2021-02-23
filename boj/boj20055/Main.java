package boj.boj20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static int[] weight;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        weight = new int[2 * N + 1];
        robot = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2 * N; i++) {
            weight[i] = stoi(st.nextToken());
        }

        int moveCount = 0;

        while (true) {
            // 벨트 이동
            int temp = weight[2 * N];
            for (int i = 2 * N; i > 1; i--) {
                weight[i] = weight[i - 1];
                if (i <= N && i > 1) {
                    robot[i] = robot[i - 1];
                }
            }
            robot[1] = false;
            weight[1] = temp;

            // 로봇 이동
            if (robot[N]) {
                robot[N] = false;
            }
            for (int i = N; i > 1; i--) {
                if (!robot[i] && robot[i - 1] && weight[i] > 0) {
                    weight[i] -= 1;
                    robot[i] = true;
                    robot[i - 1] = false;
                }
            }

            // 올라가는 위치에 로봇이 없고, 가중치가 1이상이면 로봇 올림
            if (!robot[1] && weight[1] > 0) {
                robot[1] = true;
                weight[1] -= 1;
            }

            moveCount++;
            int cnt = 0;
            for (int i = 1; i <= 2 * N; i++) {
                if (weight[i] == 0) cnt++;
            }
            if (cnt >= K) break;
        }
        System.out.println(moveCount);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
