package jungol.회전초밥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int D = stoi(st.nextToken());
        int K = stoi(st.nextToken());
        int C = stoi(st.nextToken());
        int[] sushi = new int[N + K - 1];
        int[] countSushi = new int[D + 2];
        int result = 0;

        for (int i = 0; i < N; i++) {
            sushi[i] = stoi(br.readLine());
        }
        int j = 0;
        for (int i = N; i < N + K - 1; i++) {
            sushi[i] = sushi[j++];
        }

        int count = 0;
        for (int i = 0; i < K; i++) {
            countSushi[sushi[i]]++;
            if(countSushi[sushi[i]] == 1) count++;
        }

        j = K;
        for (int i = 1; i < N; i++) {
            countSushi[sushi[i-1]]--;
            if (countSushi[sushi[i-1]] == 0) count--;
            countSushi[sushi[j]]++;
            if (countSushi[sushi[j]]== 1) count++;

            int temp = count;
            if (countSushi[C] == 0) temp++;
            result = Math.max(result, temp);
            j++;
        }
        System.out.println(result);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
