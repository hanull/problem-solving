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

    static List<Snowman> snowmanList = new ArrayList<>();

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
        Collections.sort(snowmanList, (Comparator.comparingInt(o -> o.height)));
        for (int i = 0; i < snowmanList.size() - 1; i++) {
            if (isPossible(snowmanList.get(i).x, snowmanList.get(i).y, snowmanList.get(i + 1).x, snowmanList.get(i + 1).y)) {
                answer = Math.min(answer, snowmanList.get(i + 1).height - snowmanList.get(i).height);
            }
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
