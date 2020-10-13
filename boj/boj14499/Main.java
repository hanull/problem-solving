package boj.boj14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, x, y, K;
    static int[][] map;
    static int[] order;
    static int[] dice = new int[6];
    static int[] dx = {0,0,-1,1};   // 동, 서, 북, 남
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int T = 0; T<K; T++) {
            if (rotation(order[T]))
                System.out.println(dice[0]);
        }
    }

    private static boolean rotation(int check) {
        if (!isPossible(check - 1)) return false;
        switch (check) {
            case 1:
                int tmp = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[2];
                dice[2] = tmp;
                copyNum();
                break;
            case 2:
                tmp = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[3];
                dice[3] = tmp;
                copyNum();
                break;
            case 3:
                tmp = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[4];
                dice[4] = tmp;
                copyNum();
                break;
            case 4:
                tmp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[1];
                dice[1] = tmp;
                copyNum();
                break;
        }
        return true;
    }

    private static void copyNum() {
        if (map[x][y] == 0) {
            map[x][y] = dice[5];
        } else {
            dice[5] = map[x][y];
            map[x][y] = 0;
        }
    }

    private static boolean isPossible(int check) {
        int nx = x + dx[check];
        int ny = y + dy[check];
        if (nx < 0 || nx >= N) return false;
        if (ny < 0 || ny >= M) return false;
        x = nx; y = ny;
        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        x = stoi(st.nextToken());
        y = stoi(st.nextToken());
        K = stoi(st.nextToken());
        map = new int[N][M];
        order = new int[K];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<K; i++) {
            order[i] = stoi(st.nextToken());
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
