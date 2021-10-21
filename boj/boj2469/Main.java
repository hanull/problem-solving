package boj.boj2469;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    static int K, N;
    static HashMap<Integer, Integer> target = new HashMap<>();
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = stoi(br.readLine());
        N = stoi(br.readLine());
        map = new char[N][K];
        char[] tmp = br.readLine().toCharArray();
        for (int i = 0; i < tmp.length; i++) {
            target.put(tmp[i] - 'A', i);
        }
        int x = 0;
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < K; j++) {
                if (j == K - 1) map[i][K - 1] = '*';
                else map[i][j] = input[j];
            }
            if (map[i][0] == '?') x = i;
        }
        dfs(x, 0, false);
        for (int i = 0; i < K - 1; i++) {
            System.out.print("x");
        }
    }

    private static void dfs(int x, int y, boolean flag) {
        if (y == K) {
            if (isEquals()) {
                for (int i = 0; i < K - 1; i++) {
                    System.out.print(map[x][i]);
                }
                System.exit(0);
            }
            return;
        }

        if (flag) {
            map[x][y] = '*';
            dfs(x, y + 1, false);
        } else {
            map[x][y] = '*';
            dfs(x, y + 1, false);
            if (y == K - 1) return;
            map[x][y] = '-';
            dfs(x, y + 1, true);
        }
    }

    private static boolean isEquals() {
        for (int start = 0; start < K; start++) {
            int j = start;
            for (int i = 0; i < N; i++) {
                if (map[i][j] == '*') {
                    if (j != 0 && map[i][j - 1] == '-') j--;
                } else {
                    j++;
                }
            }
            if (target.get(start) != j) return false;
        }
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
