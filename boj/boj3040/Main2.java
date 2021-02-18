package boj.boj3040;

import java.util.Scanner;

public class Main2 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dwarf = new int[9];

        int total = 0;
        for (int i = 0; i < 9; i++) {
            dwarf[i] = sc.nextInt();
            total += dwarf[i];
        }

        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (total - (dwarf[i] + dwarf[j]) == 100) {
                    for (int idx = 0; idx < 9; idx++) {
                        if (idx != i && idx != j) {
                            System.out.println(dwarf[idx]);
                        }
                    }
                }
            }
        }

    }
}
