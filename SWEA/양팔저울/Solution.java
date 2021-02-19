package SWEA.양팔저울;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[] arr;
    static int count;
    static int[] pow = new int[10];
    static int[] fac = new int[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());

        StringBuilder sb = new StringBuilder();
        init();
        for (int tc = 1; tc <= T; tc++) {
            N = stoi(br.readLine());
            arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            int totalWeight = 0;
            for (int i = 0; i < N; i++) {
                arr[i] = stoi(st.nextToken());
                totalWeight += arr[i];
            }

            count = 0;
            findAllPossilbeCase(0, 0, 0, totalWeight, new boolean[N]);
            sb.append("#" + tc + " " + count + "\n");

        }

        System.out.print(sb);

    }

    static void init() {
        pow[0] = fac[0] = 1;
        for (int i = 1; i <= 9; i++) {
            pow[i] = pow[i - 1] * 2;
            fac[i] = fac[i - 1] * i;
        }
    }

    static void findAllPossilbeCase(int idx, int leftWeight, int rightWeight, int remain, boolean[] visited) {
        if (leftWeight >= rightWeight + remain) {
            count += pow[N - idx] * fac[N - idx];
            return;
        }

        if (idx == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            findAllPossilbeCase(idx + 1, leftWeight + arr[i], rightWeight, remain - arr[i], visited);
            if (leftWeight >= rightWeight + arr[i]) {
                findAllPossilbeCase(idx + 1, leftWeight, rightWeight + arr[i], remain - arr[i], visited);
            }
            visited[i] = false;
        }

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
