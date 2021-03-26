package Basic.LIS;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_binarysearch {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N]; // 원소들 저장
        int[] LIS = new int[N]; // 각 위치 ==> LIS 길이를 만족하는 원소의 최소값

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int size = 0;
        for (int i = 0; i < N; i++) {
            int index = Arrays.binarySearch(LIS, 0, size, arr[i]);
            index = Math.abs(index) - 1;
            LIS[index] = arr[i];

            if (size == index) size++;  // 삽입 될 위치와 사이즈가 같다는 것은, 마지막의 위치에 arr[i] 값이 추가 된다는 것이다.
        }
        System.out.println(size);

    }
}
