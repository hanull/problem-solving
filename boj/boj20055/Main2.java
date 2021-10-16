package boj.boj20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static int N, K;
    static int[] conveyorBelt;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        conveyorBelt = new int[2 * N];
        robot = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            conveyorBelt[i] = stoi(st.nextToken());
        }
        int answer = 0;
        while (true) {
            answer++;

            // 벨트, 로봇 한 칸 회전
            turnBelt();

            // 로봇 이동, 칸의 내구도 1 이상
            moveRobot();

            // 0번 벨트에 로봇 올린다. (내구도 0 이상)
            putRobot();

            // 내구도가 0 인 칸의 개수가 K개 이상이면 종료
            if (checkTheEnd()) break;
        }
        System.out.println(answer);
    }

    private static void putRobot() {
        if (conveyorBelt[0] > 0) {
            robot[0] = true;
            conveyorBelt[0]--;
        }
    }

    private static void moveRobot() {
        robot[N - 1] = false;
        for (int i = N - 2; i >= 0; i--) {
            if (conveyorBelt[i + 1] >= 1 && robot[i] && !robot[i + 1]) {
                robot[i + 1] = robot[i];
                robot[i] = false;
                conveyorBelt[i + 1]--;
            }
        }
    }

    private static void turnBelt() {
        int temp = conveyorBelt[2 * N - 1];
        for (int i = 2 * N - 2; i >= 0; i--) {
            if (i <= N - 2 && i >= 0) {
                robot[i + 1] = robot[i];
                robot[i] = false;
            }
            conveyorBelt[i + 1] = conveyorBelt[i];
        }
        conveyorBelt[0] = temp;
    }

    private static boolean checkTheEnd() {
        int count = 0;
        for (int num : conveyorBelt) {
            if (num == 0) count++;
        }
        return count >= K;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
