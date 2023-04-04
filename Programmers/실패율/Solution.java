package Programmers.실패율;

import java.util.*;

public class Solution {

    static class Node {
        int no, total, fail;
        double average;

        public Node(final int no, final int total, final int fail) {
            this.no = no;
            this.total = total;
            this.fail = fail;
        }

        public void calculateAverage() {
            average = (double) fail / (double) total;
        }
    }

    public int[] solution(int N, int[] stages) {
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i + 1, 0, 0);
        }
        Set<Integer> usedStages = new HashSet<>();
        for (int stage : stages) {
            usedStages.add(stage);
            for (int i = 1; i <= stage; i++) {
                if (i > N) {
                    break;
                }
                nodes[i - 1].total++;
                if (i == stage) {
                    nodes[i - 1].fail++;
                }
            }
        }
        for (int stage : usedStages) {
            if (stage <= N) {
                nodes[stage - 1].calculateAverage();
            }
        }
        Arrays.sort(nodes, (o1, o2) -> {
            if (Double.compare(o1.average, o2.average) == 0) {
                return o1.no - o2.no;
            }
            return Double.compare(o2.average, o1.average);
        });

        int[] answer = new int[N];
        int index = 0;
        for (Node node : nodes) {
            answer[index++] = node.no;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3})));
        System.out.println(Arrays.toString(sol.solution(4, new int[]{4,4,4,4,4})));
    }
}
