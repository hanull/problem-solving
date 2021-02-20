package boj.boj1525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
6 4 7
8 5 0
3 2 1
 */

public class Main {

    static class Puzzle {
        int x, y, dist;
        int[][] map;

        public Puzzle(int x, int y, int dist, int[][] map) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.map = map;
        }
    }
    static Queue<Puzzle> q = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1 , 1};
    static HashSet<Integer> visited = new HashSet<>();
    static int minDistance = -1;
    static int[][] finalMap = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] originMap = new int[3][3];
        int x = 0;
        int y = 0;
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                originMap[i][j] = stoi(st.nextToken());
                if (originMap[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }

        movePuzzle(x, y, originMap);
        System.out.println(minDistance);
    }

    static void movePuzzle(int x, int y, int[][] originMap) {
        q.add(new Puzzle(x, y, 0, copyArrMap(originMap)));
        while (!q.isEmpty()) {
            Puzzle tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int dist = tmp.dist;
            int[][] map = tmp.map;
            if (tx == 2 && ty == 2 && isCompleteMap(map)) {
                minDistance = dist;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;

                int[][] copyMap = copyArrMap(map);
                copyMap[tx][ty] = copyMap[nx][ny];
                copyMap[nx][ny] = 0;
                int num = mapToNumber(copyMap);
                if (visited.contains(num)) continue;
                visited.add(num);
                q.add(new Puzzle(nx, ny, dist + 1, copyMap));
            }
        }
    }

    static int mapToNumber(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(map[i][j]);
            }
        }
        return stoi(new String(sb));
    }

    static boolean isCompleteMap(int[][] checkMap) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (checkMap[i][j] != finalMap[i][j]) return false;
            }
        }
        return true;
    }

    static int[][] copyArrMap(int[][] map) {
        int[][] res = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = map[i][j];
            }
        }
        return res;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= 3 || y < 0 || y >= 3 ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
