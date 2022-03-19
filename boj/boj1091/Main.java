package boj.boj1091;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = stoi(br.readLine());
        int[] cards = new int[n];
        int[] shuffleCommand = new int[n];
        int[] targetPoint = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = i % 3;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            targetPoint[i] = stoi(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            shuffleCommand[i] = stoi(st.nextToken());
        }

        int count = 0;
        while (true) {
            if (isSame(cards, targetPoint, n)) {
                break;
            }
            shuffleCards(cards, shuffleCommand, n);
            count++;

            if (isFinish(cards, n)) {
                count = -1;
                break;
            }
        }
        System.out.println(count);
    }

    private static boolean isSame(int[] cards, int[] targetPoint, int n) {
        for (int i = 0; i < n; i++) {
            if (cards[i] != targetPoint[i]) {
                return false;
            }
        }
        return true;
    }

    private static void shuffleCards(int[] cards, int[] shuffleCommand, int n) {
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = cards[shuffleCommand[i]];
        }
        for (int i = 0; i < n; i++) {
            cards[i] = temp[i];
        }
    }

    private static boolean isFinish(int[] cards, int n) {
        for (int i = 0; i < n; i++) {
            if (cards[i] != i % 3) {
                return false;
            }
        }
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
