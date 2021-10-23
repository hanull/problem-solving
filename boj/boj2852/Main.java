package boj.boj2852;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = stoi(br.readLine());
        int current = 0;
        int[] time = new int[2];
        int[] score = new int[2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int teamNo = stoi(st.nextToken()) - 1;
            int pointTime = getTime(st.nextToken());
            int gap = pointTime - current;
            if (score[0] > score[1]) {
                time[0] += gap;
            } else if (score[0] < score[1]) {
                time[1] += gap;
            }
            score[teamNo]++;
            current = pointTime;
            if (i == N - 1) {
                gap = 48 * 60 - current;
                if (score[0] > score[1]) {
                    time[0] += gap;
                } else if (score[0] < score[1]) {
                    time[1] += gap;
                }
            }
        }
        System.out.println(changeTime(time[0]));
        System.out.println(changeTime(time[1]));
    }

    private static String changeTime(int input) {
        String time = "";
        if (input / 60 < 10) time += "0" + input / 60;
        else time += String.valueOf(input / 60);
        time += ":";
        if (input % 60 < 10) time += "0" + input % 60;
        else time += String.valueOf(input % 60);
        return time;
    }

    private static int getTime(String input) {
        String[] tmp = input.split(":");
        int time = stoi(tmp[0]) * 60;
        time += stoi(tmp[1]);
        return time;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
