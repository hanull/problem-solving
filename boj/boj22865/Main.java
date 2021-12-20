package boj.boj22865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static final int MAX = 987654321;
	static int N, M;
	static int[] friends = new int[3];
	static List<Node>[] houses;
	static class Node {
		int to, weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			friends[i] = stoi(st.nextToken()) - 1;
		}
		houses = new List[N];
		for (int i = 0; i < N; i++) {
			houses[i] = new ArrayList<>();
		}
		M = stoi(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken()) - 1;
			int to = stoi(st.nextToken()) - 1;
			int weight = stoi(st.nextToken());
			houses[from].add(new Node(to, weight));
			houses[to].add(new Node(from, weight));
		}
		int[][] dist = new int[3][N];
		for (int i = 0; i < 3; i++) {
			dist[i] = getDistance(friends[i]);
		}
		int answer = 0;
		int answerVertex = 0;
		for (int i = 0; i < N; i++) {
			int closest = MAX;
			for (int j = 0; j < 3; j++) {
				closest = Math.min(closest, dist[j][i]);
			}
			if (closest > answer) {
				answerVertex = i;
				answer = closest;
			}
		}
		System.out.println(answerVertex + 1);
	}

	private static int[] getDistance(int startPoint) {
		int[] distArray = new int[N];
		Arrays.fill(distArray, MAX);
		distArray[startPoint] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
		pq.add(new Node(startPoint, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int current = node.to;
			int distance = node.weight;
			if (distArray[current] < distance) continue;
			for (Node nextNode : houses[current]) {
				int next = nextNode.to;
				int nextDist = nextNode.weight;
				if (distance + nextDist < distArray[next]) {
					distArray[next] = distance + nextDist;
					pq.add(new Node(next, distance + nextDist));
				}
			}
		}
		return distArray;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
