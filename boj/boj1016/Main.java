package boj.boj1016;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long min = sc.nextLong();
        long max = sc.nextLong();

        HashSet<Long> removeNum = new HashSet<>();
        long end = (long) Math.sqrt(max);
        for (long i = 2; i <= end; i++) {
            long powNum = i * i;
            long start = min % powNum == 0 ? min : powNum * (min / powNum) + powNum;
            for (long j = start; j <= max; j += powNum) {
                if (removeNum.contains(j)) continue;
                removeNum.add(j);
            }
        }
        System.out.println(max - min + 1 - removeNum.size());
    }
}
