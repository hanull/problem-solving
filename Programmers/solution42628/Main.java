package Programmers.solution42628;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println(Arrays.toString(sol.solution(operations)));
    }
}

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (String input : operations) {
            String[] str = input.split(" ");
            String op = str[0];
            int num = Integer.valueOf(str[1]);
            if (op.equals("I")) {   // 숫자 삽입
                pq.offer(num);
            } else if (op.equals("D") && num < 0) { // 최소값 삭제
                pq.poll();
            } else {    // 최대값 삭제
                PriorityQueue<Integer> tmp = new PriorityQueue<>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return Integer.compare(o2, o1);
                    }
                });
                tmp.addAll(pq);
                tmp.poll();
                pq.clear();
                pq.addAll(tmp);
            }
        }
        if (pq.isEmpty()) {
            return answer;
        } else {
            int max = 0;
            int min = 1000000;
            while (!pq.isEmpty()) {
                int num = pq.poll();
                if (max < num) max = num;
                if (min > num) min = num;
            }
            answer[0] = max;
            answer[1] = min;
        }
        return answer;
    }
}

class Solution2 {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (String input : operations) {
            String[] split = input.split(" ");
            String op = split[0];
            int num = Integer.valueOf(split[1]);
            switch (op) {
                case "I":
                    minQueue.add(num);
                    maxQueue.add(num);
                    break;
                case "D":
                    if (minQueue.isEmpty()) continue;
                    if (num < 0) {
                        int min = minQueue.poll();
                        maxQueue.remove(min);
                    } else {
                        int max = maxQueue.poll();
                        minQueue.remove(max);
                    }
                    break;
            }
        }
        if (minQueue.size() >= 2) {
            answer[0] = maxQueue.poll();
            answer[1] = minQueue.poll();
        }
        return answer;
    }
}