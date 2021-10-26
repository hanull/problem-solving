package boj.boj16937;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int H, W, answer;
    static int[][] map;
    static int[] selected = new int[1];
    static List<Node> arr = new ArrayList<>();
    static class Node {
        int x, y, no;

        public Node(int x, int y, int no) {
            this.x = x;
            this.y = y;
            this.no = no;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = stoi(st.nextToken());
        W = stoi(st.nextToken());
        map = new int[H][W];
        int N = stoi(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            arr.add(new Node(x, y, i));
            if (x != y) arr.add(new Node(y, x, i));
        }
        for (int i = 0; i < arr.size(); i++) {
            if (isRange(arr.get(i).x, arr.get(i).y)) {
                selected[0] = arr.get(i).no;
                pick(i, arr.get(i).x, arr.get(i).y, arr.get(i).x * arr.get(i).y);
            }
        }
        System.out.println(answer);
    }

    private static void pick(int index, int tx, int ty, int total) {
        for (int i = index + 1; i < arr.size(); i++) {
            int nx = arr.get(i).x;
            int ny = arr.get(i).y;
            int no = arr.get(i).no;
            // 같은 스티커인지 확인
            if (selected[0] == no) continue;
            // 오른쪽에 붙이기
            if (isRange(Math.max(tx, nx), ty + ny)) {
                answer = Math.max(answer, total + nx * ny);
            }
            // 아래 붙이기
            if (isRange(tx + nx, Math.max(ty, ny))) {
                answer = Math.max(answer, total + nx * ny);
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x > H || y > W ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
