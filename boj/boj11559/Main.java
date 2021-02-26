package boj.boj11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    static char[][] map = new char[12][6];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int totalCount;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        go();
        System.out.println(totalCount);
    }

    static void go() {
        boolean flag = true;

        while (flag) {
            flag = false;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] == '.') continue;
                    Deque<Node> deque = findPuyo(i, j); // 같은 색 뿌요 탐색
                    if (deque.size() >= 4) {    // 4개 이상일 때 터트릴 수 있음
                        flag = true;
                        while (!deque.isEmpty()) {
                            Node tmp = deque.pollFirst();
                            int x = tmp.x;
                            int y = tmp.y;
                            map[x][y] = '.';
                        }
                    }
                }
            }

            if (flag) {
                totalCount++;
                changeMap();
            }

        }
    }

    static void changeMap() {
        Deque<Character> deque = new ArrayDeque<>();

        // 현재 열에서 뿌요인 것 덱에 추가
        for (int j = 0; j < 6; j++) {
            for (int i = 11; i >= 0; i--) {
                if (map[i][j] != '.') {
                    deque.addLast(map[i][j]);
                    map[i][j] = '.';
                }
            }

            if (deque.size() == 0) continue;

            // 터진 공간 찾기
            int idx = 0;
            for (int i = 11; i >= 0; i--) {
                if (map[i][j] == '.') {
                    idx = i;
                    break;
                }
            }

            // 뿌요 아래부터 입력
            while (!deque.isEmpty()) {
                map[idx--][j] = deque.pollFirst();
            }
            deque.clear();
        }
    }

    static Deque<Node> findPuyo(int x, int y) {
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(x, y));

        Deque<Node> tmp = new ArrayDeque<>();
        boolean[][] visited = new boolean[12][6];
        tmp.add(new Node(x, y));
        visited[x][y] = true;
        char color = map[x][y];

        while (!tmp.isEmpty()) {
            Node node = tmp.poll();
            int tx = node.x;
            int ty = node.y;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] != color) continue;
                visited[nx][ny] = true;
                tmp.add(new Node(nx, ny));
                deque.add(new Node(nx, ny));
            }
        }

        return deque;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= 12 || y < 0 || y >= 6 ? false : true;
    }

}
