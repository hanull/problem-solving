package boj.boj16922;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static int N;
    static int[] alpa = {1, 5, 10, 50};
    static int[] isSelected;
    static HashSet<Integer> hashSet = new HashSet<>();
    static int totalCount;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        isSelected = new int[N];
        cal(0, 0);
        System.out.println(totalCount);
    }

    static void cal(int cnt, int start) {
        if (cnt == N) {
            int total = 0;
            for (int i = 0; i < N; i++) {
                total += isSelected[i];
            }
            if (!hashSet.contains(total)) {
                hashSet.add(total);
                totalCount++;
            }
            return;
        }

        for (int i = start; i < 4; i++) {
            isSelected[cnt] = alpa[i];
            cal(cnt + 1, i);
        }
    }

}
