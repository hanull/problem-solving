package boj.boj21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int[][] target;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static class Node {
        int x, y, like, empty;

        public Node(int x, int y, int like, int empty) {
            this.x = x;
            this.y = y;
            this.like = like;
            this.empty = empty;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(map[i], -1);
        target = new int[N * N][4];
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int current = stoi(st.nextToken()) - 1;
            for (int j = 0; j < 4; j++) {
                target[current][j] = stoi(st.nextToken()) - 1;
            }
            findYourSeat(current);
        }
        System.out.println(getAnswer());
    }

    private static int getAnswer() {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int like = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (!isRange(nx, ny)) continue;
                    if (checkFriend(nx, ny, map[i][j])) like++;
                }
                if (like > 0) like = (int) Math.pow(10, like - 1);
                answer += like;
            }
        }
        return answer;
    }

    private static void findYourSeat(int current) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.like == o2.like) {
                if (o1.empty == o2.empty) {
                    if (o1.x == o2.x) {
                        return o1.y - o2.y;
                    } else {
                        return o1.x - o2.x;
                    }
                } else {
                    return o2.empty - o1.empty;
                }
            } else {
                return o2.like - o1.like;
            }
        });
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == -1) {
                    int like = 0;
                    int empty = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (!isRange(nx, ny)) continue;
                        if (map[nx][ny] == -1) empty++;
                        if (checkFriend(nx, ny, current)) like++;
                    }
                    pq.add(new Node(i, j, like, empty));
                }
            }
        }
        Node node = pq.poll();
        map[node.x][node.y] = current;
    }

    private static boolean checkFriend(int nx, int ny, int current) {
        for (int i = 0; i < 4; i++) {
            if (map[nx][ny] == target[current][i]) return true;
        }
        return false;
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
