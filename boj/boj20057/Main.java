package boj.boj20057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] spreadPercent = {1, 1, 7, 7, 2, 2, 10, 10, 5};
    static int[][] spreadDX = {{-1, 1, -1, 1, -2, 2, -1, 1, 0}, {-1, -1, 0, 0, 0, 0, 1, 1, 2},
            {-1, 1, -1, 1, -2, 2, -1, 1, 0}, {1, 1, 0, 0, 0, 0, -1, -1, -2}};
    static int[][] spreadDY = {{1, 1, 0, 0, 0, 0, -1, -1, -2}, {-1, 1, -1, 1, -2, 2, -1, 1, 0},
            {-1, -1, 0, 0, 0, 0, 1, 1, 2}, {-1, 1, -1, 1, -2, 2, -1, 1, 0}};
    static int[][] map;
    static int pointX, pointY, direction, amountOfSandOutOfRange;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        pointX = pointY = N / 2;
        int moveCount = 0;
        boolean flag = true;
        while (flag) {
            if (direction == 0 || direction == 2) moveCount++;
            for (int i = 0; i < moveCount; i++) {
                int beforeSand = map[pointX][pointY];
                map[pointX][pointY] = 0;
                pointX += dx[direction];
                pointY += dy[direction];
                map[pointX][pointY] += beforeSand;

                // 모래 spread
                spread();

                if (pointX == 0 && pointY == 0) {
                    flag = false;
                    break;
                }

                // print map
//                print();
            }
            // 방향 회전
            direction = turn();
        }
        System.out.println(amountOfSandOutOfRange);
    }

    private static void print() {
        System.out.println(pointX + " " + pointY);
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < N; b++) {
                System.out.print(map[a][b] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void spread() {
        int totalMovingSand = map[pointX][pointY];   // spread y의 총 모래양
        map[pointX][pointY] = 0;

        int movedSand = 0;
        for (int i = 0; i < 9; i++) {   // 총 9좌표로 각 퍼센트만큼 추가
            int nx = pointX + spreadDX[direction][i];
            int ny = pointY + spreadDY[direction][i];
            int spreadAmount = totalMovingSand * spreadPercent[i] / 100;
            movedSand += spreadAmount;
            if (!isRange(nx, ny)) {
                amountOfSandOutOfRange += spreadAmount;
                continue;
            }
            map[nx][ny] += spreadAmount;
        }
        // 퍼트린 모레 외 남은 모레를 알파 위치에 추가한다.
        int nx = pointX + dx[direction];
        int ny = pointY + dy[direction];
        if (isRange(nx, ny)) {
            map[nx][ny] += totalMovingSand - movedSand;
        } else {
            amountOfSandOutOfRange += totalMovingSand - movedSand;
        }
    }

    private static int turn() {
        if (direction == 3) return 0;
        return direction + 1;
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
