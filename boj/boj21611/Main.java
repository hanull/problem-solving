package boj.boj21611;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] totalArray = new int[4];
    static int[] countArray = new int[4];
    static int[][] map, numberMap;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static HashMap<Integer, Node> hashMap = new HashMap<>();
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
        map = new int[N + 1][N + 1];
        numberMap = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] != 0) totalArray[map[i][j]] += 1;
            }
        }
        numbering();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = stoi(st.nextToken()) - 1;
            int power = stoi(st.nextToken());
            firstExplosion(direction, power);
            moveAndExplosion();
            setMap();
        }
        System.out.println(calculate());
    }

    private static int calculate() {
        int total = 0;
        for (int i = 1; i <= 3; i++) {
            total += countArray[i] * i;
        }
        return total;
    }

    private static void setMap() {
        int[][] newMap = new int[N + 1][N + 1];
        int index = 1;
        for (int i = 1; i < N * N; i++) {
            Node tmp = hashMap.get(i);
            int tx = tmp.x;
            int ty = tmp.y;
            int mapNumber = map[tx][ty];
            if (mapNumber == 0) break;
            int number = i + 1;
            int count = 1;
            while (number < N * N) {
                Node temp = hashMap.get(number);
                int nx = temp.x;
                int ny = temp.y;
                if (map[tx][ty] != map[nx][ny] || map[nx][ny] == 0) break;
                count++;
                number++;
            }
            if (index >= N * N) break;
            newMap[hashMap.get(index).x][hashMap.get(index).y] = count;
            index += 1;
            if (index >= N * N) break;
            newMap[hashMap.get(index).x][hashMap.get(index).y] = mapNumber;
            index += 1;
            i += count - 1;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = newMap[i][j];
            }
        }
    }

    private static void moveAndExplosion() {
        while (true) {
            move();
            if (!explosion()) break;
        }
//        print();
    }

    private static void print() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean explosion() {
        boolean flag = false;
        for (int i = 1; i <= N * N - 1; i++) {
            Node node = hashMap.get(i);
            int x = node.x;
            int y = node.y;
            if (map[x][y] == 0) break;
            if (i + 4 >= N * N) break;  // 가능한 범위 범어갈 경우
            int count = 1;
            int number = i + 1;
            while (true) {
                Node tmp = hashMap.get(number);
                int nx = tmp.x;
                int ny = tmp.y;
                if (map[x][y] != map[nx][ny]) break;
                count++;
                number++;
            }
            if (count >= 4) {
                countArray[map[x][y]] += count;
                flag = true;
                for (int n = i; n < number; n++) {
                    Node temp = hashMap.get(n);
                    map[temp.x][temp.y] = 0;
                }
            }
            i += count - 1;
        }
        return flag;
    }

    private static void move() {
        Queue<Integer> queue = new LinkedList<>();
        int number = N * N - 1;
        int start = 0;
        while (number > 0) {
            Node node = hashMap.get(number);
            if (map[node.x][node.y] != 0) {
                if (start == 0) start = number;
                queue.add(map[node.x][node.y]);
            }
            --number;
        }
        number = queue.size();
        int end = number;
        for (int i = start; i > end; i--) {
            Node tmp = hashMap.get(i);
            map[tmp.x][tmp.y] = 0;
        }
        while (number > 0) {
            Node node = hashMap.get(number);
            map[node.x][node.y] = queue.poll();
            --number;
        }
    }

    private static void firstExplosion(int direction, int power) {
        int tx = (N + 1) / 2;
        int ty = (N + 1) / 2;
        for (int i = 1; i <= power; i++) {
            int nx = tx + dx[direction] * i;
            int ny = ty + dy[direction] * i;
            map[nx][ny] = 0;
        }
    }

    private static void numbering() {
        int[] ax = {0, 1, 0, -1};
        int[] ay = {1, 0, -1, 0};
        int number = N * N - 1;
        int direction = 0;
        int x = 1;
        int y = 1;
        numberMap[x][y] = number;
        hashMap.put(number, new Node(x, y));
        --number;
        while (number > 0) {
            int nx = x + ax[direction];
            int ny = y + ay[direction];
            if (!isRange(nx, ny) || numberMap[nx][ny] != 0) {
                nx -= ax[direction];
                ny -= ay[direction];
                if (direction == 3) direction = 0;
                else direction += 1;
                continue;
            }
            numberMap[nx][ny] = number;
            hashMap.put(number, new Node(nx, ny));
            x = nx;
            y = ny;
            --number;
        }
    }

    private static boolean isRange(int nx, int ny) {
        return nx <= 0 || nx > N || ny <= 0 || ny > N ? false : true;
    }


    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
