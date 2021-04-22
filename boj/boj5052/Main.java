package boj.boj5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int T = stoi(br.readLine());
        while (T-- > 0) {
            int N = stoi(br.readLine());
            String[] numbers = new String[N];
            for (int i = 0; i < N; i++) {
                numbers[i] = br.readLine();
            }
            Arrays.sort(numbers);
            result.append(checkNumber(numbers) ? "YES" : "NO");
            result.append("\n");
        }
        System.out.print(result);
    }

    private static boolean checkNumber(String[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i].startsWith(numbers[i+1])) return false;
            if (numbers[i + 1].startsWith(numbers[i])) return false;
        }
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
