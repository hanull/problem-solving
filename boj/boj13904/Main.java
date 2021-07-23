package boj.boj13904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
4
5 100
2 2
2 2
2 2

answer = 104
 */

public class Main {

    static class Assignment {
        int day, weight;

        public Assignment(int day, int weight) {
            this.day = day;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = stoi(br.readLine());
        Assignment[] assignments = new Assignment[N];
        int maxDay = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int day = stoi(st.nextToken()) - 1;
            maxDay = Math.max(day, maxDay);
            assignments[i] = new Assignment(day, stoi(st.nextToken()));
        }
        Arrays.sort(assignments, ((o1, o2) -> o2.weight - o1.weight));
        int[] score = new int[maxDay + 1];
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = assignments[i].day; j >= 0; j--) {
                if (score[j] == 0) {
                    score[j] = assignments[i].weight;
                    break;
                }
            }
        }
        for (int i = 0; i <= maxDay; i++) {
            answer += score[i];
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
