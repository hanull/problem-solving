package SWEA.숫자만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[] num;
    static int[] selectOperator;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = stoi(br.readLine());
            num = new int[N];
            selectOperator = new int[N - 1];
            st = new StringTokenizer(br.readLine());
            int idx = 0;
            for (int i = 0; i < 4; i++) {   // 0: '+' , 1: '-', 2: '*', 3: '/'
                int cnt = stoi(st.nextToken());
                for (int j = 0; j < cnt; j++) {
                    selectOperator[idx++] = i;
                }
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                num[i] = stoi(st.nextToken());
            }

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            do {
                int tmp = cal();
                max = Math.max(max, tmp);
                min = Math.min(min, tmp);
            } while(nextPermutation());
            sb.append("#").append(tc).append(" ").append(max - min).append("\n");
        }
        System.out.print(sb);
    }

    static int cal() {
        int res = num[0];
        for (int i = 0; i < N - 1; i++) {
            if (selectOperator[i] == 0) {
                res += num[i + 1];
            } else if (selectOperator[i] == 1) {
                res -= num[i + 1];
            } else if (selectOperator[i] == 2) {
                res *= num[i + 1];
            } else {
                res /= num[i + 1];
            }
        }
        return res;
    }

    static boolean nextPermutation() {
        int i = N - 2;
        while (i > 0 && selectOperator[i-1] >= selectOperator[i]) i--;

        if (i == 0) return false;

        int j = N - 2;
        while (selectOperator[i-1] >= selectOperator[j]) j--;

        swap(i - 1, j);

        j = N - 2;
        while (i < j) {
            swap(i++, j--);
        }

        return true;
    }

    static void swap(int i, int j) {
        int temp = selectOperator[i];
        selectOperator[i] = selectOperator[j];
        selectOperator[j] = temp;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
