package boj.boj17179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        int L = stoi(st.nextToken());
        int[] cuttingPoint = new int[M + 1];
        for (int i = 0; i < M; i++) {
            cuttingPoint[i] = stoi(br.readLine());
        }
        cuttingPoint[M] = L;
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int count = stoi(br.readLine());
            answer.append(findLength(cuttingPoint, L, count)).append("\n");
        }
        System.out.print(answer);
    }

    private static int findLength(int[] cuttingPoint, int maxLen, int cuttingCount) {
        int answer = 0;
        int left = 0;
        int right = maxLen;
        while (left <= right) {
            int mid = (left + right) / 2;
            int current = 0;
            int total = 0;
            for (int i = 0; i < cuttingPoint.length; i++) {
                if (cuttingPoint[i] - current < mid) continue;
                total++;
                current = cuttingPoint[i];
            }
            if (total > cuttingCount) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
