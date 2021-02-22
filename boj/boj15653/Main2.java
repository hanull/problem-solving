package boj.boj15653;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main2 {

    static int N, M;
    static char[][] map;
    static int destinationX, destinationY;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][][][] visited;
    static int dist = -1;
    static Deque<Ball> balls = new ArrayDeque<>();
    static class Ball {
        Node redBall, BlueBall;
        int cnt;

        public Ball(Node redBall, Node blueBall, int cnt) {
            this.redBall = redBall;
            this.BlueBall = blueBall;
            this.cnt = cnt;
        }
    }

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
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        Ball tmp = new Ball(null, null, 0);
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'O') {
                    destinationX = i;
                    destinationY = j;
                } else if (map[i][j] == 'R') {
                    tmp.redBall = new Node(i, j);
                } else if (map[i][j] == 'B') {
                    tmp.BlueBall = new Node(i, j);
                }
            }
        }
        balls.add(tmp); // 빨강, 파랑공의 좌표 저장

        move();
        System.out.println(dist);
    }

    static void move() {
        visited[balls.peek().redBall.x][balls.peek().redBall.y][balls.peek().BlueBall.x][balls.peek().BlueBall.y] = true;

        while (!balls.isEmpty()) {
            Ball tmp = balls.pollFirst();
            int cnt = tmp.cnt;
            int rx = tmp.redBall.x;
            int ry = tmp.redBall.y;
            int bx = tmp.BlueBall.x;
            int by = tmp.BlueBall.y;

            for (int i = 0; i < 4; i++) {
                int nRX = rx;
                int nRY = ry;
                int nBX = bx;
                int nBY = by;

                // 벽('#')이나 구멍('O')을 만나기 전 까지 이동
                // 빨강공 이동
                while (map[nRX][nRY] != '#' && map[nRX][nRY] != 'O') {
                    nRX += dx[i];
                    nRY += dy[i];
                }
                if (map[nRX][nRY] == '#'){
                    nRX -= dx[i];
                    nRY -= dy[i];
                }
                // 파랑공 이동
                while (map[nBX][nBY] != '#' && map[nBX][nBY] != 'O') {
                    nBX += dx[i];
                    nBY += dy[i];
                }
                if (map[nBX][nBY] == '#'){
                    nBX -= dx[i];
                    nBY -= dy[i];
                }

                // 파랑색 공이 구멍에 빠졌을 경우, 안됨.
                if (nBX == destinationX && nBY == destinationY) continue;

                if (nRX == destinationX && nRY == destinationY) {
                    dist = cnt + 1;
                    return;
                }

                // 빨강, 파랑공이 같은 위치에 있다면, 두 공이 이동한 거리를 구한 뒤 재배치한다.
                // 둘 중 적게 움직인 공이 뒤로 1칸 이동
                if (nRX == nBX && nRY == nBY) {
                    int redDist = Math.abs(rx - nRX) + Math.abs(ry - nRY);
                    int blueDist = Math.abs(bx - nBX) + Math.abs(by - nBY);
                    if (redDist > blueDist) {
                        nRX -= dx[i];
                        nRY -= dy[i];
                    } else {
                        nBX -= dx[i];
                        nBY -= dy[i];
                    }
                }
                if (visited[nRX][nRY][nBX][nBY]) continue;
                visited[nRX][nRY][nBX][nBY] = true;
                balls.add(new Ball(new Node(nRX, nRY), new Node(nBX, nBY), cnt + 1));
            }
        }

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
