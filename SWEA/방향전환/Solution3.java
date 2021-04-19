package SWEA.방향전환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3 {

    static int startX, startY, endX, endY;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            startX = stoi(st.nextToken());
            startY = stoi(st.nextToken());
            endX = stoi(st.nextToken());
            endY = stoi(st.nextToken());

            answer = Integer.MAX_VALUE;
            move(false);
            move(true);
            result.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(result);
    }

    static void move(boolean f) {
        int result = 0;
        int tx = startX;
        int ty = startY;
        boolean flag = f;

        int count = 0;
        while (true) {
            if (tx == endX && ty == endY) {
                answer = Math.min(answer, count);
                break;
            }
            if (flag) {
                if (tx > endX) {
                    tx--;
                } else {
                    tx++;
                }
                flag = false;
            } else {
                if (ty > endY) {
                    ty--;
                } else {
                    ty++;
                }
                flag = true;
            }
            count++;
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
