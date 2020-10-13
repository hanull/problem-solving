package boj.boj14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr = new int[4][8];
    static int K;
    static int[] wheelNum, direction;
    static boolean[] flag = new boolean[3]; // 1번 2번, 2번 3번, 3번 4번

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve() {
        for (int i=0; i<K; i++) {
            go(wheelNum[i]-1, direction[i]);
        }
        int res = cal();
        return res;
    }

    private static void go(int cur, int dir) {
        setIsRotation();
        rotation(cur, dir); //  현재 바퀴 회전

        // 해당 바퀴의 오른쪽 바퀴 회전
        int d = dir;
        for (int i=cur; i<3; i++) {
            if (flag[i]) {
                d = d==1 ? -1 : 1;
                rotation(i+1, d);
            } else
                break;
        }
        // 해당 바퀴의 왼쪽 바퀴 회전
        d = dir;
        for (int i=cur-1; i>=0; i--) {
            if (flag[i]) {
                d = d==1 ? -1 : 1;
                rotation(i, d);
            } else
                break;
        }
    }

    private static void rotation(int cur, int dir) {
        switch (dir) {
            case 1:     // right
                int tmp = arr[cur][7];
                for (int i=6; i>=0; i--){
                    arr[cur][i+1] = arr[cur][i];
                }
                arr[cur][0] = tmp;

                break;
            case -1:    // left
                tmp = arr[cur][0];
                for (int i=1; i<8; i++){
                    arr[cur][i-1] = arr[cur][i];
                }
                arr[cur][7] = tmp;
                break;
        }
    }

    private static void setIsRotation() {
        flag[0] = arr[0][2] == arr[1][6] ? false : true;
        flag[1] = arr[1][2] == arr[2][6] ? false : true;
        flag[2] = arr[2][2] == arr[3][6] ? false : true;
    }

    private static int cal() {
        int total = 0;
        int[] hap = {1, 2, 4, 8};
        for (int i=0; i<4; i++){
            if (arr[i][0] == 1) total += hap[i];
        }
        return total;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0; i<4; i++) {
            String[] str = br.readLine().split("");
            for (int j=0; j<8; j++) {
                arr[i][j] = stoi(str[j]);
            }
        }
        K = stoi(br.readLine());
        wheelNum = new int[K];
        direction = new int[K];
        for (int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            wheelNum[i] = stoi(st.nextToken());
            direction[i] = stoi(st.nextToken());
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
