package Programmers.복서정렬하기;

import java.util.*;

public class Solution {

    static class Boxer {
        int no, win, draw, lose, bigger, weight;
        double percent;

        public Boxer(int no, int win, int draw, int lose, int bigger, double percent, int weight) {
            this.no = no;
            this.win = win;
            this.draw = draw;
            this.lose = lose;
            this.bigger = bigger;
            this.percent = percent;
            this.weight = weight;
        }
    }

    public int[] solution(int[] weights, String[] head2head) {
        List<Boxer> lists = new ArrayList<>();
        for (int i = 0; i < weights.length; i++) lists.add(new Boxer(i + 1, 0, 0, 0, 0, 0, weights[i]));
        for (int i = 0; i < head2head.length; i++) {
            for (int j = 0; j < head2head[i].length(); j++) {
                if (head2head[i].charAt(j) == 'N') {
                    lists.get(i).draw += 1;
                } else if (head2head[i].charAt(j) == 'L') {
                    lists.get(i).lose += 1;
                } else {
                    lists.get(i).win += 1;
                    if (weights[i] < weights[j]) lists.get(i).bigger += 1;
                }
            }
        }
        for (int i = 0; i < weights.length; i++) {
            int totalGame = lists.get(i).win + lists.get(i).lose;
            if (totalGame > 0) lists.get(i).percent = (double) lists.get(i).win / totalGame * 100;
        }
        Collections.sort(lists, (o1, o2) -> {
            if (o2.percent != o1.percent) {
                return Double.compare(o2.percent, o1.percent);
            }
            if (o2.bigger != o1.bigger) {
                return Integer.compare(o2.bigger, o1.bigger);
            }
            if (o2.weight != o1.weight) {
                return Integer.compare(o2.weight, o1.weight);
            }
            return Integer.compare(o1.no, o2.no);
        });
        int[] answer = new int[weights.length];
        for (int i = 0; i < weights.length; i++) {
            answer[i] = lists.get(i).no;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(new int[]{50, 82, 75, 120}, new String[]{"NLWL", "WNLL", "LWNW", "WWLN"})));
        System.out.println(Arrays.toString(sol.solution(new int[]{145, 92, 86}, new String[]{"NLW","WNL","LWN"})));
        System.out.println(Arrays.toString(sol.solution(new int[]{60, 70, 60}, new String[]{"NNN","NNN","NNN"})));
    }
}
