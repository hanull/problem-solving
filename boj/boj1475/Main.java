package boj.boj1475;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.next();
        int[] number = new int[10];
        for (int i = 0; i < N.length(); i++) {
            int num = N.charAt(i) - '0';
            if (num == 9) {
                number[6] += 1;
            } else {
                number[num] += 1;
            }
        }
        int max = 0;
        number[6] = number[6] % 2 >  0 ? number[6] / 2 + 1 : number[6] / 2;
        for (int i = 0; i < 10; i++) {
            if (max < number[i]) max = number[i];
        }
        System.out.println(max);
    }
}
