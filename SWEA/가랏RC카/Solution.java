package SWEA.가랏RC카;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, speed, distance;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int T = stoi(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = stoi(br.readLine());
            speed = 0;
            distance = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int command = stoi(st.nextToken());
                if (command == 1) {
                    speed += stoi(st.nextToken());
                } else if (command == 2){
                    speed -= stoi(st.nextToken());
                    if (speed < 0) speed = 0;
                }
                distance += speed;
            }
            System.out.println("#" +tc + " " + distance);
        }


    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
