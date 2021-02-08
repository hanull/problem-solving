package SWEA.햄버거다이어트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, L;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Food[] foods;
    static int bestCombScore;

    static class Food {
        int score, kcal;

        public Food(int score, int kcal) {
            this.score = score;
            this.kcal = kcal;
        }
    }

    public static void main(String[] args) throws IOException {
        int T = stoi(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            L = stoi(st.nextToken());
            foods = new Food[N];
            bestCombScore = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int score = stoi(st.nextToken());
                int kcal = stoi(st.nextToken());
                foods[i] = new Food(score, kcal);
            }

            comb(0, 0, 0);
            System.out.println("#" + tc + " " + bestCombScore);
        }
    }

    static void comb(int start, int score, int kcal) {
        if (kcal > L) {
            return;
        }

        bestCombScore = Math.max(bestCombScore, score);
        for (int i = start; i < N; i++) {
            comb(i + 1, score + foods[i].score, kcal + foods[i].kcal);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
