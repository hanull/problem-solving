package Programmers.주차요금계산;

import java.util.*;

public class Solution {

    static class Node {
        int in, totalTime;
        boolean flag;

        public Node(final int in, final int totalTime, final boolean flag) {
            this.in = in;
            this.totalTime = totalTime;
            this.flag = flag;
        }
    }

    static int MAX_OUT_TIME = 1439;

    public int[] solution(int[] fees, String[] records) {
        TreeMap<String, Node> histories = new TreeMap<>();
        for (String record : records) {
            String[] temp = record.split(" ");
            int time = toTime(temp[0]);
            String carNumber = temp[1];
            String flag = temp[2];
            if (flag.equals("IN")) {
                if (histories.containsKey(carNumber)) {
                    Node node = histories.get(carNumber);
                    node.in = time;
                    node.flag = false;
                    histories.put(carNumber, node);
                } else {
                    histories.put(carNumber, new Node(time, 0, false));
                }
            } else {
                Node node = histories.get(carNumber);
                node.totalTime += time - node.in;
                node.flag = true;
                histories.put(carNumber, node);
            }
        }
        int[] answer = new int[histories.keySet().size()];
        int index = 0;
        for (String key : histories.keySet()) {
            answer[index++] = calculateFee(fees, histories.get(key));
        }
        return answer;
    }

    private int toTime(final String str) {
        String[] temp = str.split(":");
        return Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
    }

    private int calculateFee(final int[] fees, Node node) {
        int useTime = node.totalTime;
        if (!node.flag) {
            useTime += MAX_OUT_TIME - node.in;
        }

        if (useTime <= fees[0]) {
            return fees[1];
        }
        useTime -= fees[0];
        return (int) (fees[1] + Math.ceil((double) useTime / (double) fees[2]) * fees[3]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(new int[]{180, 5000, 10, 600},
                new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
                        "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"})));
        System.out.println(Arrays.toString(sol.solution(new int[]{120, 0, 60, 591},
                new String[]{"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"})));
        System.out.println(Arrays.toString(sol.solution(new int[]{180, 5000, 10, 600},
                new String[]{"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"})));
    }
}
