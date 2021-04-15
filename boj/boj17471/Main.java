package boj.boj17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] population;
    static int[][] vertex;
    static int totalPopulation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        population = new int[N + 1];
        vertex = new int[N + 1][N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = stoi(st.nextToken());
            totalPopulation += population[i];
        }
        dfs();

    }

    static void dfs() {

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
