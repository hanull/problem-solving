package boj.boj1038;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if (N <= 10) {
            System.out.println(N);
        } else if (N > 1022) {
            System.out.println(-1);
        } else {
            Queue<Long> queue = new LinkedList<>();
            for (long i = 0; i < 10; i++) {
                queue.add(i);
            }
            int index = 9;
            while (!queue.isEmpty()) {
                long currentNumber = queue.poll();
                long units = currentNumber % 10;
                for (int i = 0; i < units; i++) {
                    long nextNumber = currentNumber * 10 + i;
                    queue.add(nextNumber);
                    index++;
                    if (index == N) {
                        System.out.println(nextNumber);
                        return;
                    }
                }
            }
        }
    }
}
