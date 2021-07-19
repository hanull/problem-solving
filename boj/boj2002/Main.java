package boj.boj2002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
6
a1
a2
a3
a4
a5
a6
a2
a4
a3
a1
a6
a5
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        HashMap<String, Integer> hashMap = new HashMap<>();
        int[] arr = new int[N];
        int[] tmp = new int[N];
        for (int i = 0; i < N; i++) {
            hashMap.put(br.readLine(), i);
            arr[i] = tmp[i] = i;
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int carNumber = hashMap.get(br.readLine());
            int index = i;
            boolean flag = false;
            for (int j = i + 1; j < N; j++) {
                if (carNumber == arr[index]) {
                    index++;
                    break;
                }
                flag = true;
                tmp[j] = arr[index++];
            }
            if (flag) {
                tmp[i] = carNumber;
                answer++;
            }
            for (int j = 0; j < N; j++) arr[j] = tmp[j];
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
