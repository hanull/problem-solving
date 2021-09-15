package boj.boj14467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = stoi(br.readLine());
        int[] number = new int[101];
        Arrays.fill(number, -1);
        int answer = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int no = stoi(st.nextToken());
            int point = stoi(st.nextToken());
            if (number[no] == -1) {
                number[no] = point;
            } else if (number[no] != -1 && number[no] != point) {
                number[no] = point;
                answer++;
            }

        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
