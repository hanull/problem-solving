package SWEA.힙;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            int N = sc.nextInt();
            result.append("#").append(tc).append(" ");
            for (int i = 0; i < N; i++) {
                int operation = sc.nextInt();
                if (operation == 1) {
                    pq.add(sc.nextInt());
                } else {
                    if (pq.isEmpty()) {
                        result.append(-1).append(" ");
                    } else result.append(pq.poll()).append(" ");
                }
            }
            result.append("\n");
        }
        System.out.print(result);
    }
}
