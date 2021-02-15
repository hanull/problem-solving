package Basic.permutation;

import java.util.Arrays;
import java.util.Scanner;

public class NextPermutation {

    static int N;
    static int[] input;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }
        Arrays.sort(input);

        do {
            System.out.println(Arrays.toString(input));
        } while (nextPermutation());
    }

    static boolean nextPermutation() {
        int i = N - 1;
        while (i > 0 && input[i-1] >= input[i]) i--;

        // 더 큰 순열이 없다 -> 마지막 순열
        if (i == 0) return false;

        // 더 큰 수를 찾을 때 까지 인덱스 이동
        int j = N - 1;
        while (input[i-1] >= input[j]) j--;

        swap(i - 1, j);

        // 변경한 값 뒤 쪽을 오름차순으로 변경
        j = N - 1;
        while (i < j) {
            swap(i++, j--);
        }

        return true;
    }

    static void swap(int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

}
