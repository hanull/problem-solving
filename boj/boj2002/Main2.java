package boj.boj2002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            hashMap.put(br.readLine(), i);
        }
        int[] arrivedCar = new int[N];
        for (int i = 0; i < N; i++) {
            arrivedCar[i] = hashMap.get(br.readLine());
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (arrivedCar[i] > arrivedCar[j]) {
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
