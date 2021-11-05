package boj.boj11508;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        List<Integer> list = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            list.add(stoi(br.readLine()));
        }
        Collections.sort(list, ((o1, o2) -> o2 - o1));
        int answer = 0;
        int count = 0;
        for (Integer price : list) {
            count++;
            if (count == 3) {
                count = 0;
                continue;
            }
            answer += price;
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
