package boj.boj3980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1
10 0 0 0 0 0 0 0 0 0 0
0 10 0 0 0 0 0 0 0 0 0
0 0 10 1 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0 0
0 0 0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 0 0 1

answer : 29
 */
public class Main {

    static int[][] playerStatus;
    static int[] position;
    static int maxPower;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        while (T-- > 0) {
            playerStatus = new int[11][11];
            position = new int[11];
            maxPower = 0;
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    playerStatus[i][j] = stoi(st.nextToken());
                }
            }
            pickPlayer(0, 0);
            System.out.println(maxPower);
        }
    }

    private static void pickPlayer(int index, int total) {
        if (index == 11) {
            if (isPossible()) maxPower = Math.max(maxPower, total);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if (playerStatus[index][i] == 0) continue;
            if (position[i] >= 2) continue;
            position[i]++;
            pickPlayer(index + 1, total + playerStatus[index][i]);
            position[i]--;
        }
    }

    private static boolean isPossible() {
        for (int i = 0; i < 11; i++) {
            if (position[i] == 0) return false;
        }
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
