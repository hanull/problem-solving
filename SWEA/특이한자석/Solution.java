package SWEA.특이한자석;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int[][] gear;
    static boolean[] magnetFlag;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            gear = new int[4][8];
            magnetFlag = new boolean[3];
            K = stoi(br.readLine());
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    gear[i][j] = stoi(st.nextToken());
                }
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int no = stoi(st.nextToken()) - 1;
                int direction = stoi(st.nextToken());
                go(no, direction);
            }
            int totalPoint = calculateTotalPoint();
            result.append("#").append(tc).append(" ").append(totalPoint).append("\n");
        }
        System.out.print(result);
    }

    private static int calculateTotalPoint() {
        int result = 0;
        int mul = 1;
        for (int i = 0; i < 4; i++) {
            result += gear[i][0] == 0 ? 0 : mul;
            mul *= 2;
        }
        return result;
    }

    private static void go(int no, int direction) {
        checkMagnetFlag();
        rotation(no, direction);
        if (no == 0) {
            right(no, direction);
        } else if (no == 1 || no == 2) {
            left(no, direction);
            right(no, direction);
        } else {
            left(no, direction);
        }
    }

    private static void left(int no, int direction) {
        for (int i = no - 1; i >= 0; i--) {
            if (!magnetFlag[i]) break;
            direction *= -1;
            rotation(i, direction);
        }
    }

    private static void right(int no, int direction) {
        for (int i = no; i < 3; i++) {
            if (!magnetFlag[i]) break;
            direction *= -1;
            rotation(i + 1, direction);
        }
    }
    private static void rotation(int no, int direction) {
        if (direction == 1) {
            int tmp = gear[no][7];
            for (int i = 7; i >= 1; i--) {
                gear[no][i] = gear[no][i - 1];
            }
            gear[no][0] = tmp;
        } else {
            int tmp = gear[no][0];
            for (int i = 0; i < 7; i++) {
                gear[no][i] = gear[no][i + 1];
            }
            gear[no][7] = tmp;
        }
    }

    private static void checkMagnetFlag() {
        magnetFlag[0] = gear[0][2] == gear[1][6] ? false : true;
        magnetFlag[1] = gear[1][2] == gear[2][6] ? false : true;
        magnetFlag[2] = gear[2][2] == gear[3][6] ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
