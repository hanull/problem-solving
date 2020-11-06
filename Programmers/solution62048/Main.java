package Programmers.solution62048;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(8, 12));
    }
}


class Solution {
    public long solution(int w, int h) {
        long gcd = BigInteger.valueOf(w).gcd(BigInteger.valueOf(h)).longValue();
        long x = h;
        long y = w;
        return ((x * y) - ((x / gcd) + (y / gcd) - 1) * gcd);
    }
}