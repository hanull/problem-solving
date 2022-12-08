package naver_financial.test3;

import java.util.PriorityQueue;

class Solution {

    static class Info {
        int amount, price, average;

        public Info(final int amount, final int price, final int average) {
            this.amount = amount;
            this.price = price;
            this.average = average;
        }
    }

    public int solution(int n, int[][] battery) {
        PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.average == o2.average) {
                return o1.amount - o2.amount;
            }
            return o1.average - o2.average;
        });

        for (final int[] batteryInfo : battery) {
            pq.add(new Info(batteryInfo[0], batteryInfo[1], batteryInfo[1] / batteryInfo[0]));
        }

        int answer = Integer.MAX_VALUE;
        int totalPrice = 0;
        int leftAmount = n;
        while (!pq.isEmpty()) {
            final Info info = pq.poll();
            final int amount = info.amount;
            final int price = info.price;

            final int buyAmount = leftAmount / amount;
            answer = Math.min(answer, totalPrice + (price * (buyAmount + 1)));
            totalPrice += buyAmount * price;
            leftAmount -= buyAmount * amount;
            if (leftAmount <= 0) {
                answer = Math.min(answer, totalPrice);
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(50, new int[][]{{10, 100000}, {4, 35000}, {1, 15000}}));
        System.out.println(sol.solution(20, new int[][]{{6, 30000}, {3, 18000}, {4, 28000}, {1, 9500}}));
    }
}
