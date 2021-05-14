package boj.boj15806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, T;
    static boolean[][][] visited;
    static int[] dx = {-1, -1, -2, -2, 1, 1, 2, 2};
    static int[] dy = {-2, 2, -1, 1, -2, 2, -1, 1};
    static Deque<Node> moldList;
    static Node[] checkPointList;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        T = stoi(st.nextToken());
        if (M == 0 || K == 0) {
            System.out.println("NO");
            System.exit(0);
        }
        visited = new boolean[N][N][2];
        moldList = new ArrayDeque<>();
        checkPointList = new Node[K];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken()) - 1;
            int y = stoi(st.nextToken()) - 1;
            visited[x][y][0] = true;
            moldList.add(new Node(x, y));
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken()) - 1;
            int y = stoi(st.nextToken()) - 1;
            checkPointList[i] = new Node(x, y);
        }
        System.out.println(isClean() ? "NO" : "YES");
    }

    private static boolean isClean() {
        int time = spreadMold();
        for (Node target : checkPointList) {
            if (visited[target.x][target.y][time % 2]) return false;
        }
        return true;
    }

    private static int spreadMold() {
        int time = 0;
        while (T-- > 0) {
            int size = moldList.size();
            for (int i = 0; i < size; i++) {
                Node tmp = moldList.pollFirst();
                int tx = tmp.x;
                int ty = tmp.y;
                visited[tx][ty][time % 2] = false;
                for (int d = 0; d < 8; d++) {
                    int nx = tx + dx[d];
                    int ny = ty + dy[d];
                    if(!isRange(nx, ny)) continue;
                    if(visited[nx][ny][(time + 1) % 2]) continue;
                    visited[nx][ny][(time + 1) % 2] = true;
                    moldList.addLast(new Node(nx, ny));
                }
            }
            time++;
        }
        return time;
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
