package boj.boj15686_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<Pair> bbq = new ArrayList<>();
    static ArrayList<Pair> house = new ArrayList<>();
    static boolean[] visited;
    static int min = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = stoi(st.nextToken());
                if (tmp == 1) {
                    house.add(new Pair(i, j));
                } else if (tmp == 2) {
                    bbq.add(new Pair(i, j));
                }
            }
        }
        visited = new boolean[bbq.size()];
        dfs(0, 0);
        System.out.println(min);
    }

    private static void dfs(int idx, int cnt) {
        if (cnt == M) {
            int cityDist = 0;
            for (Pair housePoint : house) {
                int totalDist = 100;
                for (int i = 0; i < bbq.size(); i++) {
                    if (!visited[i]) continue;
                    int dist = Math.abs(housePoint.x - bbq.get(i).x) + Math.abs(housePoint.y - bbq.get(i).y);
                    totalDist = Math.min(dist, totalDist);
                }
                cityDist += totalDist;
            }
            min = Math.min(min, cityDist);
            return;
        }

        for (int i = idx; i < bbq.size(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(i + 1, cnt + 1);
            visited[i] = false;
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}