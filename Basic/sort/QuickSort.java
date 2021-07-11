package Basic.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {3, 2, 4, 5, 1};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int index = partition(array, start, end);

            quickSort(array, start, index - 1);
            quickSort(array, index, end);
        }
    }

    private static int partition(int[] array, int start, int end) {
        int left = start;
        int right = end;
        int pivot = array[(left + right) / 2];

        while (left <= right) {
            while (array[left] < pivot) left++;
            while (array[right] > pivot) right--;
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[right];
        array[right] = array[left];
        array[left] = temp;
    }

}
