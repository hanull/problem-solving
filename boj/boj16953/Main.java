package boj.boj16953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int totalCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long startNumber = stoi(st.nextToken());
        long targetNumber = stoi(st.nextToken());

        changeNumber(startNumber, targetNumber, 0);
        System.out.println(totalCount == Integer.MAX_VALUE ? -1 : totalCount + 1);
    }

    static void changeNumber(long currentNumber, long targetNumber, int count) {
        if (currentNumber == targetNumber) {
            totalCount = Math.min(count, totalCount);
            return;
        }

        if (currentNumber > targetNumber) return;
        changeNumber(currentNumber * 10 + 1, targetNumber, count + 1);
        changeNumber(currentNumber * 2, targetNumber, count + 1);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
