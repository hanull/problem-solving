package Test.kakaoIntern.test2;

import java.util.*;

public class Solution {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static class Node {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int i = 0; i < 5; i++) {
            char[][] map = new char[5][5];
            int idx = 0;
            for (int j = 0; j < 5; j++) {
                map[idx++] = places[i][j].toCharArray();
            }
            answer[i] = checkSafeDistance(map);
        }
        return answer;
    }

    private int checkSafeDistance(char[][] map) {
        Queue<Node> participants = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 'P') {
                    participants.add(new Node(i, j, 0));
                }
            }
        }
        while (!participants.isEmpty()) {
            Node participant = participants.poll();
            Queue<Node> queue = new LinkedList<>();
            queue.add(participant);
            boolean[][] visited = new boolean[5][5];
            visited[participant.x][participant.y] = true;
            while (!queue.isEmpty()) {
                Node tmp = queue.poll();
                int tx = tmp.x;
                int ty = tmp.y;
                int dist = tmp.dist;
                for (int d = 0; d < 4; d++) {
                    int nx = tx + dx[d];
                    int ny = ty + dy[d];
                    if (!isRange(nx, ny)) continue;
                    if (visited[nx][ny]) continue;
                    if (map[nx][ny] == 'X') continue;
                    if (dist == 2) continue;
                    if (map[nx][ny] == 'P') return 0;
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny, dist + 1));
                }
            }
        }
        return 1;
    }

    private boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= 5 || ny < 0 || ny >= 5 ? false : true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[][] input = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        System.out.println(Arrays.toString(sol.solution(input)));
    }
}
