package Basic.sort;

import java.util.Arrays;

public class BubbleSort {

    static int[] arr = {3, 2, 4, 5, 1};
    static int N;

    public static void main(String[] args) {
        N = arr.length;
        bubbleSort();
        System.out.println(Arrays.toString(arr));
    }

    static void bubbleSort() {
        for (int i = N - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

}
