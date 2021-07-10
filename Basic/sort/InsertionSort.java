package Basic.sort;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] array = {3, 2, 4, 5, 1};
        int[] answer = insertionSort(array);
        System.out.println(Arrays.toString(answer));
    }

    private static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int prev = i - 1;
            while (prev >= 0 && array[prev] > temp) {
                array[prev + 1] = array[prev];
                prev--;
            }
            array[prev + 1] = temp;
        }
        return array;
    }
}
