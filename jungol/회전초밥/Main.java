package jungol.회전초밥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
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

        for (int i = 0; i < N; i++) {
            sushi[i] = stoi(br.readLine());
        }
        int j = 0;
        for (int i = N; i < N + K - 1; i++) {
            sushi[i] = sushi[j++];
        }

        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < K; i++) {
            hashSet.add(sushi[i]);
            countSushi[sushi[i]]++;
        }
        int maxCount = hashSet.size();
        if (!hashSet.contains(C)) maxCount++;

        j = K;
        for (int i = 1; i < N; i++) {
            countSushi[sushi[i-1]]--;
            if (countSushi[sushi[i-1]] == 0) hashSet.remove(sushi[i - 1]);

            countSushi[sushi[j]]++;
            hashSet.add(sushi[j]);
            int count = hashSet.size();
            if (!hashSet.contains(C)) count++;
            maxCount = Math.max(maxCount, count);
            j++;
        }
        System.out.println(maxCount);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
