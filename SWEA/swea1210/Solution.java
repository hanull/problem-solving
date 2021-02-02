package SWEA.swea1210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T;
    static int[][] map;
    static final int MAX_SIZE = 100;
    static int x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 1; tc <= 10; tc++) {
            T = stoi(br.readLine());
            map = new int[MAX_SIZE][MAX_SIZE];
            for (int i = 0; i < MAX_SIZE; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < MAX_SIZE; j++) {
                    map[i][j] = stoi(st.nextToken());
                    if (map[i][j] == 2) {
                        x = i;
                        y = j;
                    }
                }
            }

            for (int i = x - 1; i > 0; i--) {
                // 왼쪽에 사다리가 있어서 이동할 경우
                if (y - 1 >= 0 && map[i][y - 1] == 1) {
                    while (y - 1 >= 0 && map[i][y - 1] == 1) {
                        y--;
                    }
                }
                // 오른쪽에 사다리가 있어서 이동할 경우
                else if (y + 1 < MAX_SIZE && map[i][y + 1] == 1) {
                    while (y + 1 < MAX_SIZE && map[i][y + 1] == 1) {
                        y++;
                    }
                }
            }

            System.out.println("#" + tc + " " + y);
        }

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
