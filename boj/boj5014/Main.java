package boj.boj5014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int start, end, goal, up, down;
    static int[] arr;
    static boolean[] visited;
    static int[] dx = new int[2];
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(start, 0));
        visited[start] = true;
        while(!q.isEmpty()) {
            Pair tmp = q.poll();
            int cur = tmp.x;
            int cnt = tmp.cnt;
            if (cur == goal) {
                min = Math.min(min, cnt);
            }
            for (int i=0; i<2; i++) {
                int next = cur+dx[i];
                if (next<1 || next>end || visited[next]) continue;
                visited[next] = true;
                q.add(new Pair(next, cnt + 1));
            }
        }
        if (min == Integer.MAX_VALUE) System.out.println("use the stairs");
        else System.out.println(min);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        end = stoi(st.nextToken());
        start = stoi(st.nextToken());
        goal = stoi(st.nextToken());
        up = stoi(st.nextToken());
        down = stoi(st.nextToken());
        arr = new int[end + 1];
        visited = new boolean[end + 1];
        dx[0]=up; dx[1]=-down;
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Pair {
    int x, cnt;
    public Pair(int x, int cnt) {
        this.x = x;
        this.cnt = cnt;
    }
}