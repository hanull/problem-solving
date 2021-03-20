package boj.boj2023;

import java.util.Scanner;

public class Main2 {

    static int N;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] startNumber = {2, 3, 5, 7};

        for (int start : startNumber) {
            dfs(1, start);
        }
        System.out.println(result);
    }

    static void dfs(int count, int number) {
        if (count == N) {
            result.append(number).append("\n");
            return;
        }

        for (int index = 1; index <= 9; index++) {
            int nextNumber = number * 10 + index;
            if (isPrime(nextNumber)) {
                dfs(count + 1, nextNumber);
            }
        }
    }

    static boolean isPrime(int number) {
        for (int index = 2; index * index <= number; index++) {
            if (number % index == 0) return false;
        }
        return true;
    }

}
