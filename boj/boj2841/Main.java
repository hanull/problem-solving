package boj.boj2841;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int P = stoi(st.nextToken());
        Stack<Integer>[] stacks = new Stack[8];
        for (int i = 1; i <= 7; i++) stacks[i] = new Stack<>();

        int answer = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int line = stoi(st.nextToken());
            int pret = stoi(st.nextToken());
            if (stacks[line].isEmpty()) {
                stacks[line].add(pret);
                answer++;
            } else {
                while (!stacks[line].isEmpty() && stacks[line].peek() > pret) {
                    stacks[line].pop();
                    answer++;
                }
                if (!stacks[line].isEmpty() && stacks[line].peek() == pret) continue;
                stacks[line].add(pret);
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
