package Test.nhncommerce.test3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
3 4 5
5 8 4 1
4 6 3 7
2 4 10 1

21


4 4 4
8 8 2 10
10 4 9 9
10 4 9 2
3 7 4 10

31
 */
public class Main {

    static int N, M, K, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        map = new int[N][M * 2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int index, int count) {
        if (count == K) {
            answer = Math.max(answer, calculate());
            System.out.println(answer);
            return;
        }
        for (int i = index; i < N; i++) {
            for (int push = 0; push < M; push++) {
                if (count + push > K) break;
                if (push > 0) moveBlock(true, i, push); // 블록 이동
                dfs(i + 1, count + push);
                if (push > 0) moveBlock(false, i, push);
            }
        }
    }

    private static void moveBlock(boolean flag, int row, int moveCount) {
        if (flag) {
            for (int i = M - 1; i >= 0; i--) {
                map[row][i + moveCount] = map[row][i];
                map[row][i] = 0;
            }
        } else {
            for (int i = 0; i < M; i++) {
                map[row][i] = map[row][i + moveCount];
                map[row][i + moveCount] = 0;
            }
        }
    }

    private static int calculate() {
        int max = 0;
        for (int j = 0; j < M * 2; j++) {
            int total = 0;
            for (int i = 0; i < N; i++) {
                total += map[i][j];
            }
            max = Math.max(max, total);
        }
        return max;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
