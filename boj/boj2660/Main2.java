package boj.boj2660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

	private static int N;
	private static List<Integer>[] members;
	private static class Node {
		int member, dist;

		public Node(int member, int dist) {
			this.member = member;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = stoi(br.readLine());
		members = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			members[i] = new ArrayList<>();
		}
		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			if (isFinish(a, b)) {
				break;
			}
			members[a].add(b);
			members[b].add(a);
		}
		solve();
	}

	private static void solve() {
		int[] scores = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			scores[i] = getScore(i);
		}
		int leaderScore = findMinScore(scores);
		List<Integer> leaders = findLeaders(leaderScore, scores);
		System.out.println(leaderScore + " " + leaders.size());
		for (int i = 0; i < leaders.size(); i++) {
			System.out.print(leaders.get(i));
			if (i < leaders.size() - 1) {
				System.out.print(" ");
			}
		}
	}

	private static List<Integer> findLeaders(int leaderScore, int[] scores) {
		List<Integer> leaders = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (scores[i] == leaderScore) {
				leaders.add(i);
			}
		}
		return leaders;
	}

	private static int findMinScore(int[] scores) {
		return Arrays.stream(scores)
			.filter(score -> score != 0)
			.min()
			.getAsInt();
	}

	private static int getScore(int memberNo) {
		int[] scores = new int[N + 1];
		Queue<Node> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		queue.add(new Node(memberNo, 0));
		visited[memberNo] = true;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int member = node.member;
			int dist = node.dist;
			for (Integer friend : members[member]) {
				if (visited[friend]) {
					continue;
				}
				visited[friend] = true;
				scores[friend] = dist + 1;
				queue.add(new Node(friend, dist + 1));
			}
		}
		return findMaxScore(scores);
	}

	private static int findMaxScore(int[] scores) {
		return Arrays.stream(scores)
			.max()
			.getAsInt();
	}

	private static boolean isFinish(int a, int b) {
		return a == -1 && b == -1;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
