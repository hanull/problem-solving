package boj.boj2485;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int tmp = sc.nextInt();
            arr[i] = tmp;
            list.add(arr[i]);
        }
        int gcd = arr[1] - arr[0];
        for (int i = 2; i < n; i++) {
            arr[i] = sc.nextInt();
            list.add(arr[i]);
            int gap = arr[i] - arr[i - 1];
            gcd = BigInteger.valueOf(gap).gcd(BigInteger.valueOf(gcd)).intValue();
        }
        int start = arr[0];
        int end = arr[arr.length - 1];
        int cnt = (end - start) / gcd - (n - 2) - 1;
        System.out.println(cnt);
    }
}
