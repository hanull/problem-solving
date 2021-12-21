package boj.boj5567;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Friend {
		int from, dist;

		public Friend(int from, int dist) {
			this.from = from;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = stoi(br.readLine());
		int M = stoi(br.readLine());
		List<Integer>[] friends = new List[N];
		for (int i = 0; i < N; i++) {
			friends[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken()) - 1;
			int to = stoi(st.nextToken()) - 1;
			friends[from].add(to);
			friends[to].add(from);
		}
		System.out.println(findFriend(friends, N));
	}

	private static int findFriend(List<Integer>[] friends, int N) {
		int answer = -1;
		PriorityQueue<Friend> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
		boolean[] visited = new boolean[N];
		pq.add(new Friend(0, 0));
		visited[0] = true;

		while (!pq.isEmpty()) {
			Friend friend = pq.poll();
			int from = friend.from;
			int dist = friend.dist;
			if (dist > 2) {
				break;
			}
			answer++;
			for (int next : friends[from]) {
				if (visited[next]) continue;
				visited[next] = true;
				pq.add(new Friend(next, dist + 1));
			}
		}
		return answer;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
