package boj.boj17779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] population;
    static int[][] zone;
    static int answer = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        for (int x = 1; x < N - 2; x++) {
            for (int y = 1; y < N - 1; y++) {
                for (int d1 = 1; d1 <= y; d1++) {
                    for (int d2 = 1; d2 <= N - 1 - y; d2++) {
                        if (x + d1 + d2 < N && y - d1 >= 0 && y + d2 < N) {
                            calculate(x, y, d1, d2);
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static void calculate(int x, int y, int d1, int d2) {
        zone = new int[N][N];
        population = new int[5];

        for (int i = 0; i < x + d1; i++) {
            int tmp = i < x ? 0 : i - x + 1;
            for (int j = 0; j <= y - tmp; j++) {
                zone[i][j] = 1;
            }
        }

        for (int i = 0; i <= x + d2; i++) {
            int tmp = i <= x ? 0 : i - x;
            for (int j = y + 1 + tmp; j < N; j++) {
                zone[i][j] = 2;
            }
        }

        for (int i = x + d1; i < N; i++) {
            int tmp = i - (x + d1);
            for (int j = 0; j < y - d1 + tmp; j++) {
                if (j < y - d1 + d2) zone[i][j] = 3;
            }
        }

        int tmp = d1;
        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y + d2 - d1 + tmp; j--) {
                zone[i][j] = 4;
            }
            tmp--;
            if (tmp < 0) tmp = 0;
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (zone[i][j] == 0) {
                    population[4] += map[i][j];
                } else if (zone[i][j] == 1) {
                    population[0] += map[i][j];
                } else if (zone[i][j] == 2) {
                    population[1] += map[i][j];
                } else if (zone[i][j] == 3){
                    population[2] += map[i][j];
                } else if (zone[i][j] == 4){
                    population[3] += map[i][j];
                }
            }
        }
        Arrays.sort(population);
        answer = Math.min(answer, population[4] - population[0]);
    }


    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
