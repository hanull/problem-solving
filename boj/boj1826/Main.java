package boj.boj1826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L, P;
    static PriorityQueue<Node> gasStation = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.point, o2.point);
        }
    });
    static PriorityQueue<Node> nextStation = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o2.amount, o1.amount);
        }
    });
    static int cur;
    static class Node {
        int point, amount;

        public Node(int point, int amount) {
            this.point = point;
            this.amount = amount;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            gasStation.add(new Node(stoi(st.nextToken()), stoi(st.nextToken())));
        }
        st = new StringTokenizer(br.readLine());
        L = stoi(st.nextToken());
        P = stoi(st.nextToken());
        cur = P;

        boolean flag = true;
        int cnt = 0;
        while (cur < L) {
            // 갈 수 있는 모든 주유소 저장
            while (!gasStation.isEmpty() && gasStation.peek().point <= cur) {
                nextStation.add(gasStation.poll());
            }
            if (nextStation.isEmpty()) {
                flag = false;
                break;
            }

            Node next = nextStation.poll();
            cur += next.amount;
            cnt++;
        }

        if (flag) {
            System.out.println(cnt);
        } else {
            System.out.println(-1);
        }

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
