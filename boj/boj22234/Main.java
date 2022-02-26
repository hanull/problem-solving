package boj.boj22234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int no, time, enterTime, check;

		public Node(int no, int time, int enterTime, int check) {
			this.no = no;
			this.time = time;
			this.enterTime = enterTime;
			this.check = check;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		int T = stoi(st.nextToken());
		int W = stoi(st.nextToken());
		Deque<Node> queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int no = stoi(st.nextToken());
			int time = stoi(st.nextToken());
			queue.add(new Node(no, time, no, 0));
		}
		int M = stoi(br.readLine());
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.enterTime));
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int no = stoi(st.nextToken());
			int time = stoi(st.nextToken());
			int enterTime = stoi(st.nextToken());
			priorityQueue.add(new Node(no, time, enterTime, 0));
		}
		for (int i = 1; i <= W; i++) {
			Node node = queue.pollFirst();
			if (node.check == T) {
				if (node.time > 0) {
					node.check = 0;
					queue.addLast(node);
				}
				i--;
			} else {
				node.time--;
				node.check++;
				System.out.println(node.no);
				if (node.time > 0) {
					queue.addFirst(node);
				}
			}
			if (!priorityQueue.isEmpty() && priorityQueue.peek().enterTime <= i) {
				queue.addLast(priorityQueue.poll());
			}
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
