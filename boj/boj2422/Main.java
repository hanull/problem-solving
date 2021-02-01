package boj.boj2422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[][] badComb;
    static boolean[] iceCream;
    static int totalCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        badComb = new boolean[N + 1][N + 1];
        iceCream = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            badComb[a][b] = true;
            badComb[b][a] = true;
        }
        searchAllComb(1, 0);
        System.out.println(totalCount);
    }

    static void searchAllComb(int idx, int cnt) {
        if (cnt == 3) {
            if (isMix()) totalCount++;
            return;
        }
        for (int i = idx; i <= N; i++) {
            if (iceCream[i]) continue;
            iceCream[i] = true;
            searchAllComb(i + 1, cnt + 1);
            iceCream[i] = false;
        }
    }

    static boolean isMix() {
        for (int i = 1; i <= N; i++) {
            if (!iceCream[i]) continue;
            for (int j = i + 1; j <= N; j++) {
                if (!iceCream[j]) continue;
                if (badComb[i][j]) return false;
            }
        }
        return true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
