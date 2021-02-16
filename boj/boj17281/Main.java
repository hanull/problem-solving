package boj.boj17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] playerHitList;
    static int[] isSelectedPlayer = new int[10];
    static boolean[] checkedPlayer = new boolean[10];
    static int maxScore, gamePoint;
    static Deque<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        playerHitList = new int[N][10];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                playerHitList[i][j] = stoi(st.nextToken());
            }
        }

        isSelectedPlayer[4] = 1;
        checkedPlayer[4] = true;
        selectPlayer(2);
        System.out.println(maxScore);
    }

    static void selectPlayer(int playerNo) {
        if (playerNo == 10) {
            initGame(); // 점수 초기화, 선수 배치

            for (int innings = 0; innings < N; innings++) {
                playGame(innings);
            }

            maxScore = Math.max(maxScore, gamePoint);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (checkedPlayer[i]) continue;
            isSelectedPlayer[i] = playerNo;
            checkedPlayer[i] = true;
            selectPlayer(playerNo + 1);
            checkedPlayer[i] = false;
        }
    }

    static void playGame(int innings) {
        int[] base = new int[4];    // 홈, 1루, 2루, 3루

        int outCount = 0;
        while (outCount < 3) {
            int player = q.pollFirst();
            q.addLast(player);

            int swing = playerHitList[innings][player];

            if (swing == 0) {       // 아웃, 아웃 카운트 증가 후 덱 맨 뒤로
                outCount++;
            }

            else if (swing == 4) {    // 홈런, 베이스에 있는 모든 주자 홈으로
                for (int i = 1; i <= 3; i++) {
                    base[0] += base[i];
                }
                base[1] = base[2] = base[3] = 0;
                base[0]++;
            }

            else if (swing >= 1 && swing <= 3) {  // 1루타, 2루타, 3루타
                for (int i = 0; i < swing; i++) {   // 베이스에 있는 주자를 먼저 이동 후, 베이스에 새로운 주자 추가
                    base[0] += base[3];
                    base[3] = base[2];
                    base[2] = base[1];
                    base[1] = 0;
                }
                base[swing] = 1;
            }
        }
        gamePoint += base[0];
    }

    static void initGame() {
        gamePoint = 0;
        q = new ArrayDeque<>();
        for (int i = 1; i < 10; i++) {
            q.addLast(isSelectedPlayer[i]);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
