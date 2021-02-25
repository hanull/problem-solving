package Basic.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] list = {5, 3, 2, 4, 1};
        System.out.println(Arrays.toString(list));
        mergeSort(list, 0, list.length - 1);
        System.out.println(Arrays.toString(list));
    }

    static void mergeSort(int[] list, int start, int end) {
        if (start == end) return;

        int mid = (start + end) / 2;
        mergeSort(list, 0, mid);        // 왼쪽 집합
        mergeSort(list, mid + 1, end);  // 오른쪽 집합

        // 정렬된 두 집합을 이용하여 병합
        merge(list, start, mid, end);

    }

    static void merge(int[] list, int start, int mid, int end) {
        int[] newArray = new int[end - start + 1];
        int left = start;
        int right = mid + 1;

        int i = 0;
        do {
            if (list[left] < list[right]) {
                newArray[i++] = list[left++];
            } else {
                newArray[i++] = list[right++];
            }
        } while (left <= mid && right <= end);

        while (left <= mid) {
            newArray[i++] = list[left++];
        }

        while (right <= end) {
            newArray[i++] = list[right++];
        }

        System.arraycopy(newArray, 0, list, start, newArray.length);
    }

}
