package boj.boj17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, answer;
    static Node[][] map, tempMap;
    static List<Node> sharkList;
    static class Node {
        int x, y, speed, direction, size;

        public Node(int x, int y, int speed, int direction, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        map = new Node[N + 1][M + 1];
        tempMap = new Node[N + 1][M + 1];
        int anglerPoint = 0;
        if (K == 0) {
            System.out.println(0);
            System.exit(0);
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int n = stoi(st.nextToken());
            int m = stoi(st.nextToken());
            int s = stoi(st.nextToken());
            int d = stoi(st.nextToken()) - 1;
            int z = stoi(st.nextToken());
            map[n][m] = new Node(n, m, s, d, z);
        }

        while (anglerPoint < M) {
            findShark();
            anglerPoint++;
            fishing(anglerPoint);
            moveShark();
        }
        System.out.println(answer);
    }

    private static void findShark() {
        sharkList = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] != null) {
                    sharkList.add(map[i][j]);
                }
            }
        }
    }

    private static void fishing(int anglerPoint) {
        for (int i = 1; i <= N; i++) {
            if (map[i][anglerPoint] != null) {
                answer += map[i][anglerPoint].size;
                sharkList.remove(map[i][anglerPoint]);
                map[i][anglerPoint] = null;
                break;
            }
        }
    }

    private static void moveShark() {
        for (Node node : sharkList) {
            int tx = node.x;
            int ty = node.y;
            map[tx][ty] = null;
            int speed = node.speed;
            int direction = node.direction;
            int size = node.size;
            while (speed > 0) {
                int tmp;
                if (direction == 0) {   //  상
                    tmp = tx - speed;
                    speed = tmp <= 0 ? speed - (tx - 1) : 0;
                    if (tmp <= 0) {
                        tx = 1;
                        direction = 1;
                    }
                    else tx = tmp;
                } else if (direction == 1) {    // 하
                    tmp = tx + speed;
                    speed = tmp > N ? tmp - N : 0;
                    if (tmp > N) {
                        tx = N;
                        direction = 0;
                    }
                    else tx = tmp;
                } else if (direction == 2) {    // 우
                    tmp = ty + speed;
                    speed = tmp > M ? tmp - M : 0;
                    if (tmp > M) {
                        ty = M;
                        direction = 3;
                    }
                    else ty = tmp;
                } else {    // 좌
                    tmp = ty - speed;
                    speed = tmp <= 0 ? (tmp - 1) * -1 : 0;
                    if (tmp <= 0) {
                        ty = 1;
                        direction = 2;
                    }
                    else ty = tmp;
                }
            }
            node.x = tx;
            node.y = ty;
            node.direction = direction;
            if (tempMap[tx][ty] != null) {
                if (tempMap[tx][ty].size < size) {
                    tempMap[tx][ty] = node;
                }
            } else {
                tempMap[tx][ty] = node;
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] = tempMap[i][j];
                tempMap[i][j] = null;
            }
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
