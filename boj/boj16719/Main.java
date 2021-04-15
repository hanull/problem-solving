package boj.boj16719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[] input;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        visited = new boolean[input.length];
        solve(0, input.length - 1);
    }

    static void solve(int start, int end) {
        int idx = 0;
        int min = 100;
        for (int i = start; i <= end; i++) {
            if (!visited[i] && input[i] - 'A' < min) {
                min = input[i] - 'A';
                idx = i;
            }
        }
        if (min == 100) return;
        visited[idx] = true;
        for (int i = 0; i < input.length; i++) {
            if (visited[i]) {
                System.out.print(input[i]);
            }
        }
        System.out.println();
        solve(idx + 1, end);
        solve(start, idx - 1);
    }

}
