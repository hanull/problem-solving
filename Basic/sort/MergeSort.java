package Basic.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = {3, 2, 4, 5, 1};
        mergeSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    private static void merge(int[] array, int start, int mid, int end) {
        int[] left = Arrays.copyOfRange(array, start, mid + 1);
        int[] right = Arrays.copyOfRange(array, mid + 1, end + 1);

        int leftIndex = 0;
        int rightIndex = 0;
        int index = start;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                array[index++] = left[leftIndex++];
            } else {
                array[index++] = right[rightIndex++];
            }
        }
        while (leftIndex < left.length) {
            array[index++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            array[index++] = right[rightIndex++];
        }
    }

}
