package boj.boj15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
    int r,c,d;
    public Node (int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

public class Main {

    static int N;
    static int[][] map = new int[101][101];
    static int[] dr = {0,-1,0,1};   // 동, 북, 서, 남
    static int[] dc = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = stoi(st.nextToken());
            int r = stoi(st.nextToken());
            int d = stoi(st.nextToken());
            int g = stoi(st.nextToken());
            solve(c, r, d, g);
        }
        System.out.println(getCount());
    }

    private static int getCount() {
        int cnt = 0;
        for (int i=0; i<100; i++) {
            for (int j=0; j<100; j++) {
                if (isPossible(i,j)) cnt++;
            }
        }
        return cnt;
    }

    private static boolean isPossible(int r, int c) {
        if (map[r][c] != 1) return false;
        if (map[r][c+1] != 1) return false;
        if (map[r+1][c] != 1) return false;
        if (map[r+1][c+1] != 1) return false;
        return true;
    }

    private static void solve(int c, int r, int d, int generation) {
        int a = r + dr[d];
        int b = c + dc[d];
        map[r][c] = 1;
        map[a][b] = 1;
        Stack<Node> originStack = new Stack<>();
        originStack.push(new Node(a,b,d));
        int go = 0;
        while (go < generation) {
            go++;
            Stack<Node> tmpStack = copy(originStack);
            Node lastPoint = tmpStack.peek();
            int tr = lastPoint.r;
            int tc = lastPoint.c;
            while(!tmpStack.isEmpty()) {
                Node tmp = tmpStack.pop();
                int nextD = turn(tmp.d);
                int nr = tr+dr[nextD];
                int nc = tc+dc[nextD];
                map[nr][nc] = 1;
                originStack.push(new Node(nr,nc,nextD));
                tr = nr;
                tc = nc;
            }
        }
    }

    private static int turn(int d) {
        if (d==0) return 1;
        else if (d==1) return 2;
        else if (d==2) return 3;
        else return 0;
    }

    private static Stack<Node> copy(Stack<Node> originStack) {
        return (Stack<Node>) originStack.clone();
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
