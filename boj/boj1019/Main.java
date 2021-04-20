package boj.boj1019;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] answer = new int[10];
        int target = sc.nextInt();
        int start = 1;
        int point = 1;

        while (start <= target) {
            while (start % 10 != 0 && start <= target) {
                calculate(start, answer, point);
                start++;
            }
            if (start > target) break;

            while (target % 10 != 9 && start <= target) {
                calculate(target, answer, point);
                target--;
            }

            start /= 10;
            target /= 10;
            for (int i = 0; i < 10; i++) {
                answer[i] += (target - start + 1) * point;
            }
            point *= 10;
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    private static void calculate(int number, int[] answer, int point) {
        while (number > 0) {
            answer[number % 10] += point;
            number /= 10;
        }
    }
}
