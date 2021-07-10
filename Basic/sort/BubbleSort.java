package Basic.sort;

import java.util.Arrays;

public class BubbleSort {

    static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j]  = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {3, 2, 4, 5, 1};
        int[] answer = bubbleSort(array);
        System.out.println(Arrays.toString(answer));
    }

}
