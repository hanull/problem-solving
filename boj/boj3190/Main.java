package boj.boj3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Node {
    int x, y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Info {
    int time;
    char dir;
    public Info(int time, char dir) {
        this.time = time;
        this.dir = dir;
    }
}

public class Main {

    static int N, K, L;
    static int[][] map;
    static boolean[][] visited;
    static Info[] info;
    static int countInfo = 0;
    static int time = 0;
    static int[] dx = {0,0,1,-1};   // 동, 서, 남, 북
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(time);
    }

    private static void solve() {
        Deque<Node> snake = new LinkedList<>();
        snake.add(new Node(1, 1));
        visited[1][1] = true;
        int curDir = 0; // 처음에는 오른쪽으로 이동
        int goalTime = info[countInfo].time;
        while (true) {
            time++;
            Node head = snake.peekLast();
            int tx = head.x;
            int ty = head.y;
            int nx = tx + dx[curDir];
            int ny = ty + dy[curDir];
            if (!isPossibleToMove(nx, ny)) return;
            if (map[nx][ny] != 1) {     // 사과가 없을 때, 꼬리 이동
                Node tail = snake.pollFirst();
                visited[tail.x][tail.y] = false;
            } else map[nx][ny] = 0;     // 사과 먹으면, 사과 제거
            // 머리 이동

            visited[nx][ny] = true;
            snake.addLast(new Node(nx, ny));
            if (time == goalTime) {     // 뱀 방향 전환
                curDir = turn(curDir, info[countInfo].dir);
                countInfo = countInfo+1==L ? L-1 : countInfo+1;
                goalTime = info[countInfo].time;
            }
        }
    }

    private static boolean isPossibleToMove(int nx, int ny) {
        if (nx <= 0 || nx > N || ny <=0 || ny > N) return false;
        if (visited[nx][ny]) return false;
        return true;
    }

    private static int turn(int curDir, char flag) {
        int res = 0;
        switch (flag) {
            case 'L':
                if (curDir == 0) res = 3;
                else if (curDir == 1) res = 2;
                else if (curDir == 2) res = 0;
                else res = 1;
                break;
            case 'D':
                if (curDir == 0) res = 2;
                else if (curDir == 1) res = 3;
                else if (curDir == 2) res = 1;
                else res = 0;
                break;
        }
        return res;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        K = stoi(br.readLine());
        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        for (int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            map[x][y] = 1;
        }
        L = stoi(br.readLine());
        info = new Info[L];
        for (int i=0; i<L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = stoi(st.nextToken());
            char dir = st.nextToken().charAt(0);
            info[i] = new Info(time, dir);
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
