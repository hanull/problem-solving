package Test.kakao.test3;

import java.util.*;

public class Solution {

    static List<Integer>[] trainRout;

    public int[] solution(int n, int[] passenger, int[][] train) {
        int[] answer = new int[2];

        trainRout = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            trainRout[i] = new ArrayList<>();
        }
        for (int i = 0; i < train.length; i++) {
            int startPoint = train[i][0];
            int endPoint = train[i][1];
            trainRout[startPoint].add(endPoint);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        int[] edgeWeight = new int[n + 1];
        edgeWeight[1] = passenger[0];
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            for (int next : trainRout[currentVertex]) {
                int nextVertex = next;
                edgeWeight[nextVertex] = edgeWeight[currentVertex] + passenger[nextVertex - 1];
                queue.add(next);
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (edgeWeight[i] >= max) {
                max = edgeWeight[i];
                answer[0] = i;
                answer[1] = max;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 4;
        int[] passenger = {2, 1, 2, 2};
        int[][] train = {{1, 2}, {1, 3}, {2, 4}};
        System.out.println(Arrays.toString(sol.solution(n, passenger, train)));

    }
}
