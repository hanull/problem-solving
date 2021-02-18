package boj.boj3040;

import java.util.Scanner;

public class Main {

    static int[] allDwarf = new int[9];
    static int[] selectedDwarf = new int[7];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            allDwarf[i] = sc.nextInt();
        }
        comb(0, 0);
    }

    static void comb(int cnt, int start) {
        if (cnt == 7) {
            if (isSevenDwarf()) {
                print();
                System.exit(0);
            }
            return;
        }
        for (int i = start; i < 9; i++) {
            selectedDwarf[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    static boolean isSevenDwarf() {
        int res = 0;
        for (int idx : selectedDwarf) {
            res += allDwarf[idx];
        }
        return res == 100 ? true : false;
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int idx : selectedDwarf) {
            sb.append(allDwarf[idx] + "\n");
        }
        System.out.print(sb);
    }

}
