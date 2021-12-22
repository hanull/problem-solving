package boj.boj2116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[][] dice;
    static int[][] diceShape = {{0, 1, 2, 3, 4, 5},
        {3, 0, 2, 5, 4, 1},
        {5, 3, 2, 1, 4, 0},
        {1, 5, 2, 0, 4, 3},
        {4, 1, 0, 3, 5, 2},
        {2, 1, 5, 3, 0, 4}
        };
    static int[] selectedShape;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        dice = new int[N][6];
        selectedShape = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = stoi(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(answer);
    }

    private static void dfs(int count) {
        if (count == N) {
            answer = Math.max(answer, calculate());
            return;
        }
        for (int i = 0; i < diceShape.length; i++) {
            if (count == 0) {
                selectedShape[0] = i;
                dfs(count + 1);
            } else {
                if (isPossible(count - 1, count, i)) {
                    selectedShape[count] = i;
                    dfs(count + 1);
                }
            }
        }
    }

    private static boolean isPossible(int bottomDice, int aboveDice, int shape) {
        return dice[bottomDice][diceShape[selectedShape[bottomDice]][0]] == dice[aboveDice][diceShape[shape][5]];
    }

    private static int calculate() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 1; j <= 4; j++) {
                max = Math.max(max, dice[i][diceShape[selectedShape[i]][j]]);
            }
            total += max;
        }
        return total;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
