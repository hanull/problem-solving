package Programmers.택배배달과수거하기;

public class Solution {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int i = n - 1;
        int j = n - 1;
        // 더이상 배달, 수거할 곳이 없을 경우, 종료
        while (i >= 0 || j >= 0) {
            // 배달 위치
            while (i >= 0 && deliveries[i] == 0) {
                i--;
            }
            // 수거 위치
            while (j >= 0 && pickups[j] == 0) {
                j--;
            }

            // 거리 갱신
            answer += (Math.max(i, j) + 1) * 2L;

            int boxCount = cap;
            // 배달
            while (i >= 0 && boxCount > 0) {
                if (deliveries[i] > boxCount) {
                    deliveries[i] -= boxCount;
                    boxCount = 0;
                } else {
                    boxCount -= deliveries[i];
                    deliveries[i] = 0;
                    while (i >= 0 && deliveries[i] == 0) {
                        i--;
                    }
                }
            }

            // 수거
            boxCount = cap;
            while (j >= 0 && boxCount > 0) {
                if (pickups[j] > boxCount) {
                    pickups[j] -= boxCount;
                    boxCount = 0;
                } else {
                    boxCount -= pickups[j];
                    pickups[j] = 0;
                    while (j >= 0 && pickups[j] == 0) {
                        j--;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(4,
                5,
                new int[]{1, 0, 3, 1, 2},
                new int[]{0, 3, 0, 4, 0}));
        System.out.println(sol.solution(2,
                7,
                new int[]{1, 0, 2, 0, 1, 0, 2},
                new int[]{0, 2, 0, 1, 0, 2, 0}));
    }
}
