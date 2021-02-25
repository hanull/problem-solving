package SWEA.프로세서연결하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1 , 1};
    static int maxConnectedCount;
    static int minWireLength;
    static ArrayList<Node> coreList;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = stoi(br.readLine());
            map = new int[N][N];
            coreList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = stoi(st.nextToken());
                    if (map[i][j] == 1 && i != 0 && j != 0) coreList.add(new Node(i, j));
                }
            }
            maxConnectedCount = 0;
            minWireLength = Integer.MAX_VALUE;
            connect(0, 0);
            sb.append("#").append(tc).append(" ").append(minWireLength).append("\n");
        }
        System.out.print(sb);

    }

    static void connect(int cnt, int totalCount) {
        if (coreList.size() - cnt + totalCount < maxConnectedCount) return;
        if (cnt == coreList.size()) {
            if (maxConnectedCount < totalCount) {
                maxConnectedCount = totalCount;
                minWireLength = cal();
            } else if (maxConnectedCount == totalCount) {
                minWireLength = Math.min(minWireLength, cal());
            }
            return;
        }

        int tx = coreList.get(cnt).x;
        int ty = coreList.get(cnt).y;
        for (int i = 0; i < 4; i++) {
            if (isPossible(tx, ty, i)){
                setMap(tx, ty, i, 2);
                connect(cnt + 1, totalCount + 1);
                setMap(tx, ty, i, 0);
            }
        }
        connect(cnt + 1, totalCount);
    }

    static void setMap(int x, int y, int d, int flag) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
            map[nx][ny] = flag;
            nx += dx[d];
            ny += dy[d];
        }
    }

    static boolean isPossible(int x, int y, int d) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
            if (map[nx][ny] >= 1) {
                return false;
            }
            nx += dx[d];
            ny += dy[d];
        }
        return true;
    }

    static int cal() {
        int wireLen = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 2) wireLen++;
            }
        }
        return wireLen;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
