package boj.boj1655;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        StringBuilder answer = new StringBuilder();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int number = sc.nextInt();
            if (minHeap.size() == maxHeap.size()) maxHeap.add(number);
            else minHeap.add(number);

            if (minHeap.size() > 0 && maxHeap.size() > 0 && minHeap.peek() < maxHeap.peek()) {
                int temp = minHeap.poll();
                minHeap.add(maxHeap.poll());
                maxHeap.add(temp);
            }
            answer.append(maxHeap.peek()).append("\n");
        }
        System.out.print(answer);
    }
}
