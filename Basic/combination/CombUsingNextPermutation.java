package Basic.combination;

import java.util.Scanner;

public class CombUsingNextPermutation {

    static int N, R;
    static int[] input, P;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        input = new int[N];
        P = new int[N];     // N 크기의 flag 배열

        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }

        int cnt = 0;
        // 뒤쪽부터 R개 만큼 1로 채우기
        while (++cnt <= R) P[N - cnt] = 1;

        do {
            for (int i = 0; i < N; i++) {
                if (P[i] == 1) System.out.print(input[i] + " ");
            }
            System.out.println();
        } while (nextPermutation());

    }

    static boolean nextPermutation() {
        // STEP1
        int i = N - 1;
        while (i > 0 && P[i-1] >= P[i]) i--;

        if (i == 0) return false;

        // STEP2
        int j = N - 1;
        while (P[i-1] >= P[j]) j--;

        // STEP3
        swap(i - 1, j);

        // STEP4
        j = N - 1;
        while (i < j) {
            swap(i++, j--);
        }

        return true;
    }

    static void swap(int i, int j) {
        int temp = P[i];
        P[i] = P[j];
        P[j] = temp;
    }

}
