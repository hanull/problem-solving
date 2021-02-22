package boj.boj10163;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int x, y, flag;

        public Node(int x, int y, int flag) {
            this.x = x;
            this.y = y;
            this.flag = flag;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[][] map = new int[101][101];
        Node[] list = new Node[N];

        for (int t = 0; t < N; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = stoi(st.nextToken());
            int y1 = stoi(st.nextToken());
            int x2 = stoi(st.nextToken());
            int y2 = stoi(st.nextToken());


            for (int i = x1; i < x1+x2; i++) {
                for (int j = y1; j < y1+y2; j++) {
                    map[i][j] = t + 1;
                }
            }

            list[t] = new Node(x1, y1, t + 1);
        }

        for (Node node : list) {
            int x = node.x;
            int y = node.y;
            int flag = node.flag;
            int cnt = 0;
            for (int i = x; i <= 100; i++) {
                for (int j = y; j <= 100; j++) {
                    if (map[i][j] == flag) cnt++;
                }
            }
            System.out.println(cnt);

        }

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
