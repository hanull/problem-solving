package boj.boj2609;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int gcd = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
        int lcm = a * b / gcd;
        System.out.println(gcd);
        System.out.println(lcm);
    }
}
