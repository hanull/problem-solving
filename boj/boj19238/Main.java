package boj.boj19238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 1000;
    static int N, M, fuel;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Position taxi;
    static HashMap<Passenger, Boolean> passengers = new HashMap<>();
    static PriorityQueue<Passenger> priorityQueue = new PriorityQueue<>((o1, o2) -> {
        if (o1.distance == o2.distance) {
            if (o1.current.x == o2.current.x) {
                return o1.current.y - o2.current.y;
            }
            return o1.current.x - o2.current.x;
        }
        return o1.distance - o2.distance;
    });
    static class Passenger {

        int no, distance;
        Position current, destination;

        public Passenger(int no, Position current, Position destination) {
            this.no = no;
            this.current = current;
            this.destination = destination;
        }
    }

    static class Position {

        int x, y, distance;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        while (M-- > 0) {
            if (fuel < 0) {
                fuel = -1;
                break;
            }
            updateDistanceByTaxi();
            Passenger passenger = moveToPassenger();
            moveToDestination(passenger);
        }
        System.out.println(fuel);
    }

    private static void updateDistanceByTaxi() {
        priorityQueue.clear();
        for (Passenger passenger : passengers.keySet()) {
            Position destination = passenger.current;
            passenger.distance = findDistance(taxi, destination);
            priorityQueue.add(passenger);
        }
    }

    private static Passenger moveToPassenger() {
        Passenger passenger = priorityQueue.poll();
        int distance = passenger.distance;

        fuel -= distance;

        taxi.x = passenger.current.x;
        taxi.y = passenger.current.y;

        return passenger;
    }

    private static void moveToDestination(Passenger passenger) {
        Position destination = passenger.destination;
        int distance = findDistance(new Position(taxi.x, taxi.y),
                new Position(destination.x, destination.y));

        fuel -= distance;
        if (fuel >= 0) {
            fuel += distance * 2;
        }

        taxi.x = destination.x;
        taxi.y = destination.y;

        passengers.remove(passenger);
    }

    private static int findDistance(Position current, Position destination) {
        if (current.x == destination.x && current.y == destination.y) {
            return 0;
        }
        Queue<Position> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Position(current.x, current.y, 0));
        visited[current.x][current.y] = true;

        while (!q.isEmpty()) {
            Position node = q.poll();
            int tx = node.x;
            int ty = node.y;
            int distance = node.distance;
            if (tx == destination.x && ty == destination.y) {
                return distance;
            }
            if (distance > MAX) {
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (visited[nx][ny] || map[nx][ny] == 1) {
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new Position(nx, ny, distance + 1));
            }
        }
        return MAX;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        fuel = stoi(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        taxi = new Position(stoi(st.nextToken()) - 1, stoi(st.nextToken()) - 1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = stoi(st.nextToken()) - 1;
            int sy = stoi(st.nextToken()) - 1;
            int ex = stoi(st.nextToken()) - 1;
            int ey = stoi(st.nextToken()) - 1;
            Position current = new Position(sx, sy);
            Position destination = new Position(ex, ey);
            Passenger passenger = new Passenger(i, current, destination);
            passengers.put(passenger, true);
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
