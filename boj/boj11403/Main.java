package boj.boj11403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[][] weightMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                weightMap[i][j] = stoi(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (weightMap[i][k] == 1 && weightMap[k][j] == 1) {
                        weightMap[i][j] = 1;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result.append(weightMap[i][j]).append(" ");
            }
            result.append("\n");
        }
        System.out.print(result);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
