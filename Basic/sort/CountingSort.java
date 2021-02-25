package Basic.sort;

import java.util.Arrays;

public class CountingSort {

    public static void main(String[] args) {
        int[] list = {69, 10, 2, 2, 16};
        System.out.println(Arrays.toString(list));
        countingSort(list);
        System.out.println(Arrays.toString(list));
    }

    static void countingSort(int[] list) {
        final int len = list.length;
        int[] result = new int[len];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            if (list[i] < min) min = list[i];
            if (list[i] > max) max = list[i];
        }

        // 배열의 최대값까지 표현 가능한 크기의 카운팅 배열 생성
        int[] count = new int[max + 1];

        // 배열 원소 카운팅
        for (int i = 0; i < len; i++) {
            count[list[i]]++;
        }

        // 카운팅 변형 : 누적합 구하기
        for (int i = min + 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // 배열 원소 하나씩 보면서 결과 배열의 각 원소값에 해당하는 위치에 채움
        for (int i = len - 1; i >= 0; i--) {
            result[count[list[i]] - 1] = list[i];
        }

        System.arraycopy(result, 0, list, 0, len);

//        for (int i = 0; i < len; i++) {
//            list[i] = result[i];
//        }

    }

}
