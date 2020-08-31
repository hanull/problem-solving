package boj.boj1436;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int num = 666;
        int res = 0;
        while (N > 0) {
            String str = String.valueOf(num);
            if (str.contains("666")) {
                N--;
                res = num;
            }
            num++;
        }
        System.out.println(res);
    }
}
