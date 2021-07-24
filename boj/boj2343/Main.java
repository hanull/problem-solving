package boj.boj2343;

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
        int[] lessonList = new int[N];
        st = new StringTokenizer(br.readLine());
        int left = 0;
        int right = 0;
        for (int i = 0; i < N; i++) {
            lessonList[i] = stoi(st.nextToken());
            left = Math.max(left, lessonList[i]);
            right += lessonList[i];
        }
        int answer = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            int total = 0;
            int count = 0;
            for (int i = 0; i < N; i++) {
                total += lessonList[i];
                if (total > mid) {
                    total = lessonList[i];
                    count++;
                }
            }
            if (count + 1 > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = Math.min(answer, mid);
            }
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
