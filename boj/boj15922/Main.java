package boj.boj15922;

import java.util.Scanner;

public class Main {

    static class Node {
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Node[] array = new Node[N];

        for (int i = 0; i < N; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            array[i] = new Node(start, end);
        }
        int totalDistance = array[0].end - array[0].start;
        int prev = array[0].end;
        for (int i = 1; i < N; i++) {
            int start = array[i].start;
            int end = array[i].end;
            if (start < prev) {
                if (end > prev) {
                    totalDistance += end - prev;
                    prev = end;
                }
            } else {
                totalDistance += end - start;
                prev = end;
            }
        }
        System.out.println(totalDistance);
    }
}
