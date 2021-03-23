package boj.boj1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = stoi(br.readLine());
        int[][] costForPainting = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                costForPainting[i][j] = stoi(st.nextToken());
            }
        }

        for (int i = 1; i < N; i++) {
            costForPainting[i][0] += Math.min(costForPainting[i - 1][1], costForPainting[i - 1][2]);
            costForPainting[i][1] += Math.min(costForPainting[i - 1][0], costForPainting[i - 1][2]);
            costForPainting[i][2] += Math.min(costForPainting[i - 1][1], costForPainting[i - 1][0]);
        }
        System.out.println(Math.min(costForPainting[N - 1][0], Math.min(costForPainting[N - 1][1], costForPainting[N - 1][2])));

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
