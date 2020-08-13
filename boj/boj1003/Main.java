package boj.boj1003;

import java.util.HashMap;
import java.util.Scanner;

class Pair{
    int zero;
    int one;

    public Pair(int zero, int one) {
        this.zero = zero;
        this.one = one;
    }
}

public class Main {
    static HashMap<Integer, Pair> hm = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        initFibo();
        for (int i = 2; i <= 40; i++) {
            Pair tmp = fibo(i);
            hm.put(i, tmp);
        }
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            System.out.println(hm.get(n).zero + " " + hm.get(n).one);
        }
    }

    private static void initFibo() {
        hm.put(0, new Pair(1, 0));
        hm.put(1, new Pair(0, 1));
    }

    private static Pair fibo(int i) {
        if (hm.containsKey(i)) {
            return hm.get(i);
        }
        Pair tmp1 = fibo(i - 1);
        Pair tmp2 = fibo(i - 2);
        Pair next = new Pair(tmp1.zero + tmp2.zero, tmp1.one + tmp2.one);
        return next;
    }
}
