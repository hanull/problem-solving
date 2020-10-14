package boj.boj14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, L;
    static int cnt = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(cnt);
    }

    private static void solve() {
        for (int i=0; i<N; i++) {
            if (isPossible(i, 0)) cnt++;
            if (isPossible(i, 1)) cnt++;
        }
    }

    private static boolean isPossible(int i, int f) {
        boolean[] visited = new boolean[N];
        switch (f) {
            case 0:     // 가로 검사
                int left = 0;
                int right = 1;
                while (left<N && right<N) {
                    if (getGap(map[i][left], map[i][right]) == 0) {    // 경사가 같은 경우
                        left++; right++;
                    } else if (getGap(map[i][left], map[i][right]) == 1) {  // 오른쪽 아래로 경사
                        int hight = map[i][right];
                        int start = right;
                        int end = start+L-1;
                        for (int a=start; a<=end; a++) {    // 경사로 설치
                            if (a>=N || map[i][a] != hight) {
                                return false;
                            }
                            visited[a] = true;
                        }
                        left = end;
                        right = left + 1;
                    } else if (getGap(map[i][left], map[i][right]) == -1) {     // 왼쪽 아래로 경사
                        int hight = map[i][left];
                        int end = left;
                        int start = left - L + 1;
                        for (int a=start; a<=end; a++) {
                            if (a<0 || a>=N || map[i][a] != hight || visited[a]) {
                                return false;
                            }
                            visited[a] = true;
                        }
                        left = right;
                        right++;
                    } else {    // 경사가 2이상 차이날 경우
                        return false;
                    }
                }
                break;

            case 1:     // 세로 검사
                left = 0;
                right = 1;
                while (left<N && right<N) {
                    if (getGap(map[left][i], map[right][i]) == 0) {    // 경사가 같은 경우
                        left++; right++;
                    } else if (getGap(map[left][i], map[right][i]) == 1) {  // 오른쪽 아래로 경사
                        int hight = map[right][i];
                        int start = right;
                        int end = start+L-1;
                        for (int a=start; a<=end; a++) {
                            if (a>=N || map[a][i] != hight) {
                                return false;
                            }
                            visited[a] = true; // 경사로 설치
                        }
                        left = end;
                        right = left + 1;
                    } else if (getGap(map[left][i], map[right][i]) == -1) {     // 왼쪽 아래로 경사
                        int hight = map[left][i];
                        int end = left;
                        int start = left - L + 1;
                        for (int a=start; a<=end; a++) {
                            if (a<0 || a>=N || map[a][i] != hight || visited[a]) {
                                return false;
                            }
                            visited[a] = true;
                        }
                        left = right;
                        right++;
                    } else {    // 경사가 2이상 차이날 경우
                        return false;
                    }
                }
                break;
        }
        return true;
    }

    private static int getGap(int leftHight, int rightHight) {
        return leftHight - rightHight;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        L = stoi(st.nextToken());
        map = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
