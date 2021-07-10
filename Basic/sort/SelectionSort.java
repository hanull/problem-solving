package Basic.sort;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] array = {3, 2, 4, 5, 1};
        int[] answer = selectionSort(array);
        System.out.println(Arrays.toString(answer));
    }

    private static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) minIndex = j;
            }
            if (minIndex != i) swap(array, i, minIndex);
        }
        return array;
    }

    private static void swap(int[] array, int i, int minIndex) {
        int temp = array[i];
        array[i] = array[minIndex];
        array[minIndex] = temp;
    }
}
