package SWEA.프로세서연결하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution2 {

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
            selecetCore(0, 0,  coreList.size());
            sb.append("#").append(tc).append(" ").append(minWireLength).append("\n");
        }
        System.out.print(sb);

    }

    static void selecetCore(int idx, int totalConnect, int n) {
        if (idx == n) {
            if (maxConnectedCount < totalConnect) {
                maxConnectedCount = totalConnect;
                minWireLength = cal();
            } else if (maxConnectedCount == totalConnect) {
                minWireLength = Math.min(minWireLength, cal());
            }
            return;
        }

        int x = coreList.get(idx).x;
        int y = coreList.get(idx).y;
        for (int i = 0; i < 4; i++) {   // 해당 방향으로 이동 가능한지 체크
            if (isPossible(x, y, i)) {
                 // 전선 설치
                setMap(x, y, i, 2);
                selecetCore(idx + 1, totalConnect + 1, n);
                setMap(x, y, i, 0);
            }
        }
        selecetCore(idx + 1, totalConnect, n);
    }

    static int cal() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 2) cnt++;
            }
        }
        return cnt;
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
            if (map[nx][ny] >= 1) return false;
            nx += dx[d];
            ny += dy[d];
        }
        return true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
