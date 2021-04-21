package boj.boj15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {

    static int answer = Integer.MAX_VALUE;
    static int N, M;
    static List<CCTV> cctvList = new ArrayList<>();
    static class CCTV {
        int x, y, type;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) cctvList.add(new CCTV(i, j, map[i][j]));
            }
        }
        search(0, cctvList.size(), map);
        System.out.println(answer);
    }

    static void search(int index, int targetIndex, int[][] map) {
        if (index == targetIndex) {
            answer = Math.min(answer, calculate(map));
            return;
        }

        int type = cctvList.get(index).type;
        int x = cctvList.get(index).x;
        int y = cctvList.get(index).y;

        // CCTY Type에 맞게 사무실 검사
        // 0:상, 1:하, 2:좌, 3:우
        switch (type) {
            case 5: // 4방향 검사
                checkMap(0, x, y, map);
                checkMap(1, x, y, map);
                checkMap(2, x, y, map);
                checkMap(3, x, y, map);
                search(index + 1, targetIndex, map);
                break;
            case 4: // 3방향 검사
                // 상, 좌, 우
                int[][] tempMap = copyMap(map);
                checkMap(0, x, y, tempMap);
                checkMap(2, x, y, tempMap);
                checkMap(3, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);

                // 상, 하, 우
                tempMap = copyMap(map);
                checkMap(0, x, y, tempMap);
                checkMap(1, x, y, tempMap);
                checkMap(3, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);

                // 하, 좌, 우
                tempMap = copyMap(map);
                checkMap(1, x, y, tempMap);
                checkMap(2, x, y, tempMap);
                checkMap(3, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);

                // 상, 하, 좌
                tempMap = copyMap(map);
                checkMap(0, x, y, tempMap);
                checkMap(1, x, y, tempMap);
                checkMap(2, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);
                break;
            case 3: // 2방향 검사
                tempMap = copyMap(map);
                checkMap(0, x, y, tempMap);
                checkMap(3, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);

                tempMap = copyMap(map);
                checkMap(3, x, y, tempMap);
                checkMap(1, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);

                tempMap = copyMap(map);
                checkMap(2, x, y, tempMap);
                checkMap(1, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);

                tempMap = copyMap(map);
                checkMap(0, x, y, tempMap);
                checkMap(2, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);
                break;
            case 2: // 좌, 우
                tempMap = copyMap(map);
                checkMap(0, x, y, tempMap);
                checkMap(1, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);

                tempMap = copyMap(map);
                checkMap(2, x, y, tempMap);
                checkMap(3, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);
                break;
            case 1: // 1방향
                tempMap = copyMap(map);
                checkMap(0, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);

                tempMap = copyMap(map);
                checkMap(1, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);

                tempMap = copyMap(map);
                checkMap(2, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);

                tempMap = copyMap(map);
                checkMap(3, x, y, tempMap);
                search(index + 1, targetIndex, tempMap);
                break;
        }
    }

    private static int calculate(int[][] map) {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) result++;
            }
        }
        return result;
    }

    private static void checkMap(int direction, int x, int y, int[][] map) {
        // CCTY Type에 맞게 사무실 검사
        // 0:상, 1:하, 2:좌, 3:우
        switch (direction) {
            case 0:         // 상
                for (int i = x - 1; i >= 0; i--) {
                    if (map[i][y] == 6) break;
                    map[i][y] = -1;
                }
                break;
            case 1:         // 하
                for (int i = x + 1; i < N; i++) {
                    if (map[i][y] == 6) break;
                    map[i][y] = -1;
                }
                break;
            case 2:         // 좌
                for (int i = y - 1; i >= 0; i--) {
                    if (map[x][i] == 6) break;
                    map[x][i] = -1;
                }
                break;
            case 3:         // 우
                for (int i = y + 1; i < M; i++) {
                    if (map[x][i] == 6) break;
                    map[x][i] = -1;
                }
                break;
        }
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
