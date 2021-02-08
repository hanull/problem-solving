package boj.boj11729;

import java.util.Scanner;

public class Main {

    static int moveCount = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        hanoi(N, 1, 2, 3);
        System.out.println(moveCount);
        System.out.println(sb);
    }

    static void hanoi(int cnt, int from, int temp, int to) {
        if (cnt == 0) return;
        hanoi(cnt - 1, from, to, temp);
        sb.append(from + " " + to + "\n");
        moveCount++;
        hanoi(cnt - 1, temp, from, to);
    }
}
