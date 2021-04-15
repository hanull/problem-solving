package boj.boj20923;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, M, doBellCount, suBellCount;
    static Deque<Integer> doDeque, suDeque, doGround, suGround;
    static {
        doDeque = new ArrayDeque<>();
        suDeque = new ArrayDeque<>();
        doGround = new ArrayDeque<>();
        suGround = new ArrayDeque<>();
    }
    static String winner;
    static boolean endFlag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            doDeque.addFirst(stoi(st.nextToken()));
            suDeque.addFirst(stoi(st.nextToken()));
        }

        playGame();
        if (endFlag) {
            System.out.println(winner);
        } else if (doBellCount > suBellCount) {
            System.out.println("do");
        } else if (suBellCount > doBellCount) {
            System.out.println("su");
        } else {
            System.out.println("dosu");
        }
    }

    static void playGame() {
        boolean turn = true;    // true - 도도, false - 수연
        while (M-- > 0) {
            if (turn) { // 도도
                doGround.addFirst(doDeque.pollFirst());
                if (doDeque.isEmpty()) {
                    winner = "su";
                    endFlag = true;
                    break;
                }
            } else {    // 수연
                suGround.addFirst(suDeque.pollFirst());
                if (suDeque.isEmpty()) {
                    winner = "do";
                    endFlag = true;
                    break;
                }
            }
            if (!doGround.isEmpty() && !suGround.isEmpty() && (doGround.peekFirst() + suGround.peekFirst()) == 5) {
                suBellCount++;
                mergeDeque(1);
            }
            if ((!doGround.isEmpty() && doGround.peekFirst() == 5) || (!suGround.isEmpty() && suGround.peekFirst() == 5)) {
                doBellCount++;
                mergeDeque(0);
            }
            turn = !turn;
        }
    }

    static void mergeDeque(int flag) {
        // 상대 그라운드 덱을 뒤집어서 합친 뒤에, 자신의 덱도 뒤집에서 합친다.
        if (flag == 0) {    // 도도가 벨을 쳤을 때
            while (!suGround.isEmpty()) {
                doDeque.addLast(suGround.pollLast());
            }
            while (!doGround.isEmpty()) {
                doDeque.addLast(doGround.pollLast());
            }
        } else {            // 수연이 벨을 쳤을 때
            while (!doGround.isEmpty()) {
                suDeque.addLast(doGround.pollLast());
            }
            while (!suGround.isEmpty()) {
                suDeque.addLast(suGround.pollLast());
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
