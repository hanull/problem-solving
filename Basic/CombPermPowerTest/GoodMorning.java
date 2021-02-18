package Basic.CombPermPowerTest;

import java.util.Arrays;

public class GoodMorning {

    static int[] num = {1, 2, 3, 4};
    static int N = 2;

    public static void main(String[] args) {

//      1. num에서 N개를 뽑아서 만들 수 있는 순열을 모두 출력하시오.
        System.out.println("----- 순열 -----");
        makePermutation(0, new int[N], new boolean[num.length]);

//      2. num에서 N개를 뽑아서 만들 수 있는 조합을 모두 출력하시오.
        System.out.println("----- 조합 -----");
        makeCombination(0, new int[N], 0);


//      3. num으로 구성할 수 있는 모든 부분집합을 출력하시오.
        System.out.println("----- 부분집합 -----");
        for (int i = 0; i < num.length; i++) {
            makeSubset(0, 0, new int[i], i);
        }


    }

    static void makePermutation(int cnt, int[] arr, boolean[] isChecked) {
        if (cnt == arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = 0; i < num.length; i++) {
            if (isChecked[i]) continue;
            arr[cnt] = num[i];
            isChecked[i] = true;
            makePermutation(cnt + 1, arr, isChecked);
            isChecked[i] = false;
        }
    }

    static void makeCombination(int cnt, int[] arr, int start) {
        if (cnt == arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = start; i < num.length; i++) {
            arr[cnt] = num[i];
            makeCombination(cnt + 1, arr, i + 1);
        }
    }

    static void makeSubset(int cnt, int start, int[] arr, int depth) {
        if (cnt == depth) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = start; i < num.length; i++) {
            arr[cnt] = num[i];
            makeSubset(cnt + 1, i + 1, arr, depth);
        }
    }

}