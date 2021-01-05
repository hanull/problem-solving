package boj.boj2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static ArrayList<Integer> houseCount = new ArrayList<>();
    static ArrayList<Node> house = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(input[j]);
                if (map[i][j] == 1) house.add(new Node(i, j));
            }
        }

        for (Node node : house) {
            if (visited[node.x][node.y]) continue;
            int cnt = bfs(node.x, node.y);
            houseCount.add(cnt);
        }

        Collections.sort(houseCount);
        System.out.println(houseCount.size());
        for (Integer cnt : houseCount) {
            System.out.println(cnt);
        }
    }

    private static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;
        int res = 1;
        while (!q.isEmpty()) {
            Node tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny] || map[nx][ny] == 0) continue;
                q.add(new Node(nx, ny));
                visited[nx][ny] = true;
                res++;
            }
        }
        return res;
    }

    private static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N ? false : true;
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}