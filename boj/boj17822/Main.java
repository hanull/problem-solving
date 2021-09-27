package boj.boj17822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, T;
    static int[][] board;
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
        T = stoi(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = stoi(st.nextToken());
            }
        }
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int d = stoi(st.nextToken());
            int k = stoi(st.nextToken());
            turn(x, d, k % M);
            remove();
        }
        System.out.println(getTotal());
    }

    private static int getTotal() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                total += board[i][j];
            }
        }
        return total;
    }

    private static void remove() {
        Queue<Node> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) continue;
                boolean flag = false;
                int index = j + 1;
                while (index < M && board[i][j] == board[i][index]) {
                    q.add(new Node(i, j));
                    q.add(new Node(i, index++));
                }
                index = j == 0 ? M - 1 : j - 1;
                while (index > 0 && board[i][j] == board[i][index]) {
                    q.add(new Node(i, j));
                    q.add(new Node(i, index--));
                }
                if (flag) board[i][j] = 0;
            }
        }
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++) {
                if (board[i][j] == 0) continue;
                boolean flag = false;
                int index = i + 1;
                while (index < N && board[i][j] == board[index][j]) {
                    q.add(new Node(i, j));
                    q.add(new Node(index++, j));
                }
                index = i - 1;
                while (index > 0 && board[i][j] == board[index][j]) {
                    q.add(new Node(i, j));
                    q.add(new Node(index--, j));
                }
                if (flag) board[i][j] = 0;
            }
        }
        if (q.size() == 0) changeNumber();
        while (!q.isEmpty()) {
            Node node = q.poll();
            board[node.x][node.y] = 0;
        }
    }

    private static void changeNumber() {
        double average = getAverage();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) continue;
                if ((double) board[i][j] == average) continue;
                if ((double) board[i][j] > average) {
                    board[i][j]--;
                } else {
                    board[i][j]++;
                }
            }
        }
    }

    private static double getAverage() {
        double numberCount = 0;
        double total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0) {
                    numberCount++;
                    total += board[i][j];
                }
            }
        }
        return total / numberCount;
    }

    private static void turn(int x, int d, int count) {
        if (d == 0) {   // 시계 방향
            for (int i = x - 1; i < N; i += x) {
                for (int move = 0; move < count; move++) {
                    int temp = board[i][M - 1];
                    for (int j = M - 2; j >= 0; j--) {
                        board[i][j + 1] = board[i][j];
                    }
                    board[i][0] = temp;
                }
            }
        } else {    // 반시계 방향
            for (int i = x - 1; i < N; i += x) {
                for (int move = 0; move < count; move++) {
                    int temp = board[i][0];
                    for (int j = 1; j < M; j++) {
                        board[i][j - 1] = board[i][j];
                    }
                    board[i][M - 1] = temp;
                }
            }
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
