package boj.boj17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parent;
    static int islandCount;
    static int[][] distance;
    static final int MAX = 987654321;
    static int[][] map, originMap;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Node>[] graph;
    static List<Edge> edgeList;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Edge implements Comparable<Edge>{
        int x, y, dist;

        public Edge(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        originMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        changeIslandNumber();
        makeSetAndInitDistance();
        findAllTheWay();
        makeGraph();
        System.out.println(calculateDistance());
    }

    static int calculateDistance() {
        int total = 0;
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(edgeList);
        int connectCont = 0;

        while (!priorityQueue.isEmpty()) {
            Edge tmp = priorityQueue.poll();
            int x = tmp.x;
            int y = tmp.y;
            int dist = tmp.dist;

            if (union(x, y)) {
                connectCont++;
                total += dist;
            }
        }
        return connectCont == islandCount - 1 ? total : -1;
    }

    static void makeGraph() {
        graph = new ArrayList[islandCount + 1];
        edgeList = new ArrayList<>();
        for (int i = 1; i <= islandCount; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= islandCount; i++) {
            for (int j = 1; j <= islandCount; j++) {
                if (distance[i][j] != MAX) graph[i].add(new Node(j, distance[i][j]));
            }
        }
        for (int i = 1; i <= islandCount; i++) {
            for (Node next : graph[i]) {
                edgeList.add(new Edge(i, next.x, next.y));
            }
        }
    }

    static void findAllTheWay() {
        for (int startPoint = 1; startPoint <= islandCount; startPoint++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == startPoint) {
                        for (int d = 0; d < 4; d++) {
                            boolean flag = true;
                            int x = i;
                            int y = j;
                            int bridgeLength = 0;
                            while (true) {
                                int nx = x + dx[d];
                                int ny = y + dy[d];
                                if (!isRange(nx, ny) || map[nx][ny] == startPoint) {
                                    flag = false;
                                    break;
                                }
                                x = nx;
                                y = ny;
                                if (map[nx][ny] != 0 && map[nx][ny] != startPoint) break;
                                bridgeLength++;
                            }
                            if (flag && bridgeLength >= 2) {
                                int nextIsland = map[x][y];
                                distance[startPoint][nextIsland] = Math.min(distance[startPoint][nextIsland], bridgeLength);
                            }
                        }
                    }
                }
            }
        }
    }

    static void changeIslandNumber() {
        islandCount = 1;
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    bfs(i, j, islandCount, visited);
                    islandCount++;
                }
            }
        }
        --islandCount;
    }

    static void bfs(int x, int y, int number, boolean[][] visited) {
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(x, y));
        map[x][y] = number;
        visited[x][y] = true;

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if (map[nx][ny] == 1) {
                    map[nx][ny] = number;
                    deque.add(new Node(nx, ny));
                }
            }
        }
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x==y) return false;
        parent[y] = x;
        return true;
    }

    static int find(int x) {
        if (x==parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void makeSetAndInitDistance() {
        parent = new int[islandCount + 1];
        for (int i = 1; i <= islandCount; i++) {
            parent[i] = i;
        }

        distance = new int[islandCount + 1][islandCount + 1];
        for (int i = 1; i <= islandCount; i++) {
            Arrays.fill(distance[i], MAX);
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
