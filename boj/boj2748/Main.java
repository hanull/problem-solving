package boj.boj2748;

import java.util.HashMap;
import java.util.Scanner;


public class Main {
    static HashMap<Integer, Long> hm = new HashMap<>();

    private static long fibo(int i) {
        if (hm.containsKey(i)) {
            return hm.get(i);
        }
        long res = fibo(i - 1) + fibo(i - 2);
        hm.put(i, res);
        return res;
    }

    private static void init() {
        hm.put(0, 0L);
        hm.put(1, 1L);
        hm.put(2, 1L);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        init();
        System.out.println(fibo(n));
    }
}
