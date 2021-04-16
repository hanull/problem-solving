package boj.boj2239;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int[][] board = new int[9][9];
    static List<Node> blackList = new ArrayList<>();
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = input.charAt(j) - '0';
                if (board[i][j] == 0) blackList.add(new Node(i, j));
            }
        }

        makeBoard(0);
    }

    static void makeBoard(int index) {
        if (index == blackList.size()) {
            print();
            System.exit(0);
        }

        for (int number = 1; number <= 9; number++) {
            Node currentNode = blackList.get(index);
            int x = currentNode.x;
            int y = currentNode.y;
            if (!isPossible(x, y, number)) continue;
            board[x][y] = number;
            makeBoard(index + 1);
            board[x][y] = 0;
        }
    }

    static boolean isPossible(int x, int y, int number) {
        // 열 체크
        for (int col = 0; col < 9; col++) {
            if (board[x][col] == number) return false;
        }

        // 행 체크
        for (int row = 0; row < 9; row++) {
            if (board[row][y] == number) return false;
        }

        // 3 * 3 체크
        int startX = x / 3 * 3;
        int startY = y / 3 * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (board[i][j] == number) return false;
            }
        }

        return true;
    }

    static void print() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                result.append(board[i][j]);
            }
            result.append("\n");
        }
        System.out.print(result);
    }

}
