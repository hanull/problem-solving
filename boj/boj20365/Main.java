package boj.boj20365;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();
        char[] arr = new char[N];
        arr[0] = input[0];
        char target = arr[0];
        int idx = 1;
        for (int i=1; i<N; i++) {
            if (target == input[i]) continue;
            arr[idx++] = input[i];
            target = input[i];
        }
        int blue = 0, red = 0;
        for (int i=0; i<idx; i++) {
            if (arr[i] == 'B') blue++;
            else red++;
        }
        int answer;
        if (blue > red) {
            answer = 1 + red;
        } else {
            answer = 1 + blue;
        }
        System.out.println(answer);
    }
}
