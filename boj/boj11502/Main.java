package boj.boj11502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N;
    static boolean flag;
    static int[] answer = new int[3];
    static List<Integer> primeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        boolean[] prime = new boolean[1001];
        for (int i = 2; i <= Math.sqrt(1000); i++) {
            for (int j = i * i; j <= 1000; j += i) {
                if (prime[j]) continue;
                prime[j] = true;
            }
        }
        for (int i = 2; i <= 1000; i++) {
            if (!prime[i]) primeList.add(i);
        }
        for (int tc = 0; tc < T; tc++) {
            N = stoi(br.readLine());
            flag = false;
            dfs(0, 0);
            if (!flag) {
                System.out.println(0);
            }
        }
    }

    private static void dfs(int count, int total) {
        if (flag) return;
        if (count == 3) {
            if (total == N) {
                for (int i = 0; i < 3; i++) {
                    System.out.print(answer[i] + " ");
                }
                System.out.println();
                flag = true;
            }
            return;
        }
        for (int i = 0; i < primeList.size(); i++) {
            int num = primeList.get(i);
            if (total + num > N) break;
            answer[count] = num;
            dfs(count + 1, total + num);
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
