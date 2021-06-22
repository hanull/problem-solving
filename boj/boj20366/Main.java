package boj.boj20366;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Snowman {
        int x, y, height;

        public Snowman(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

    static PriorityQueue<Snowman> snowmanList = new PriorityQueue<>((Comparator.comparingInt(o -> o.height)));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = stoi(br.readLine());
        int[] snow = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snow[i] = stoi(st.nextToken());
        }
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                snowmanList.add(new Snowman(i, j, snow[i] + snow[j]));
            }
        }
        int answer = Integer.MAX_VALUE;
        Snowman first = snowmanList.poll();
        int tx = first.x;
        int ty = first.y;
        int th = first.height;
        while (!snowmanList.isEmpty()) {
            Snowman snowman = snowmanList.poll();
            int nx = snowman.x;
            int ny = snowman.y;
            int nh = snowman.height;
            if (isPossible(tx, ty, nx, ny)) {
                answer = Math.min(answer, nh - th);
            }
            tx = nx;
            ty = ny;
            th = nh;
        }
        System.out.println(answer);
    }

    private static boolean isPossible(int tx, int ty, int nx, int ny) {
        return tx != nx && tx != ny && ty != nx && ty != ny;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
