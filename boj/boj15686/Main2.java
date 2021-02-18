package boj.boj15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Node> allChickenJointList = new ArrayList<>();
    static Node[] selectedChickenJoint;
    static int min = Integer.MAX_VALUE;
    static class Node {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] == 2) allChickenJointList.add(new Node(i, j, 0));
            }
        }

        for (int m = 1; m <= M; m++) {
            selectedChickenJoint = new Node[m];
            pickChickenJoint(0, 0, m);
        }
        System.out.println(min);

    }

    static void pickChickenJoint(int cnt, int start, int goal) {
        if (cnt == goal) {
            min = Math.min(min, calcCityDistance());
            return;
        }

        for (int i = start; i < allChickenJointList.size(); i++) {
            selectedChickenJoint[cnt] = allChickenJointList.get(i);
            pickChickenJoint(cnt + 1, i + 1, goal);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }


    static int calcCityDistance() {
        int res = 0;
        boolean[][] visited = new boolean[N][N];
        Queue<Node> q = new LinkedList<>();
        for (Node chickenJoint : selectedChickenJoint) {
            q.add(chickenJoint);
        }

        while (!q.isEmpty()) {
            Node tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int dist = tmp.dist;
            if (map[tx][ty] == 1) res += dist;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new Node(nx, ny, dist + 1));
            }
        }
        return res;

    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N ? false : true;
    }

}
