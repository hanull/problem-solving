package boj.boj1865;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static List<Node>[] list;
	static List<Wormhole> wormhole;
	static class Node {
		int destination, time, count;

		public Node(int destination, int time, int count) {
			this.destination = destination;
			this.time = time;
			this.count = count;
		}
	}
	static class Wormhole {
		int start, end, time;

		public Wormhole(int start, int end, int time) {
			this.start = start;
			this.end = end;
			this.time = time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int TC = stoi(br.readLine());
		while (TC-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			list = new List[N];
			wormhole = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				list[i] = new ArrayList<>();
			}
			int M = stoi(st.nextToken());
			int W = stoi(st.nextToken());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = stoi(st.nextToken()) - 1;
				int end = stoi(st.nextToken()) - 1;
				int dist = stoi(st.nextToken());
				list[start].add(new Node(end, dist, 0));
				list[end].add(new Node(start, dist, 0));
			}
			for (int i = 0; i < W; i++) {
				int start = stoi(st.nextToken()) - 1;
				int end = stoi(st.nextToken()) - 1;
				int time = stoi(st.nextToken());
				wormhole.add(new Wormhole(start, end, time));
			}
			if (isPossible()) {
				bw.write("YES\n");
			} else {
				bw.write("NO\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean isPossible() {
		boolean answer = false;
		for (int i = 0; i < N; i++) {
			for (Node node : list[i]) {

			}
		}
		return answer;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
