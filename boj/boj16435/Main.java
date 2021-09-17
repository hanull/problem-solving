package boj.boj16435;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int len = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] fruits = new int[N];
        for (int i = 0; i < N; i++) {
            fruits[i] = stoi(st.nextToken());
        }
        Arrays.sort(fruits);
        for (int fruit : fruits) {
            if (len < fruit) break;
            len++;
        }
        System.out.println(len);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
