package SWEA.규영이와인영이의카드게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int[] left = new int[9];
    static int[] right = new int[9];
    static boolean[] isSelected;
    static int lose, win;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            isSelected = new boolean[19];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) {
                int num = stoi(st.nextToken());
                isSelected[num] = true;
                left[i] = num;
            }
            lose = win = 0;
            permutation(0);
            sb.append("#" + tc + " " + win + " " + lose + "\n");
        }
        System.out.print(sb);
    }

    static void permutation(int cnt) {
        if (cnt == 9) {
            checkWinner();
            return;
        }

        for (int i = 1; i <= 18; i++) {
            if (isSelected[i]) continue;
            right[cnt] = i;
            isSelected[i] = true;
            permutation(cnt + 1);
            isSelected[i] = false;
        }
    }

    static void checkWinner() {
        int leftTotal = 0;
        int rightTotal = 0;
        for (int i = 0; i < 9; i++) {
            int total = left[i] + right[i];
            if (left[i] > right[i]) leftTotal += total;
            else if (left[i] < right[i]) rightTotal += total;
        }
        if (leftTotal > rightTotal) win++;
        else if (leftTotal < rightTotal) lose++;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
