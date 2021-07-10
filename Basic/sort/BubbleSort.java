package Basic.sort;

import java.util.Arrays;

public class BubbleSort {

    static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
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
