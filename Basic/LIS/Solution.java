package Basic.LIS;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N]; // 원소들 저장
        int[] LIS = new int[N]; // 각 원소를 마지막에 세웠을 경우으 최장 길

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            LIS[i] = 1; // i번 혼자 세웠을 때의 길이로 초기화
            for (int j = i - 1; j >= 0; j--) {  // i번 앞의 LIS와 비교
                if (arr[j] < arr[i] && LIS[j] + 1 > LIS[i]) {
                    LIS[i] = LIS[j] + 1;
                }
            }
            maxLength = Math.max(LIS[i], maxLength);
        }
        System.out.println(maxLength);

    }
}
