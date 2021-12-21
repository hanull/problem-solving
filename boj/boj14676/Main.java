package boj.boj14676;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		int M = stoi(st.nextToken());
		int K = stoi(st.nextToken());
		List<Integer>[] lists = new List[N];
		int[] needs = new int[N];
		for (int i = 0; i < N; i++) {
			lists[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken()) - 1;
			int y = stoi(st.nextToken()) - 1;
			lists[x].add(y);
			needs[y]++;
		}
		int[] constructed = new int[N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int command = stoi(st.nextToken());
			int building = stoi(st.nextToken()) - 1;
			if (!isPossible(command, building, lists, constructed, needs)) {
				System.out.println("Lier!");
				System.exit(0);
			}
		}
		System.out.println("King-God-Emperor");
	}

	private static boolean isPossible(int command, int building, List<Integer>[] lists, int[] constructed, int[] needs) {
		if (command == 1) {
			if (needs[building] > 0) {
				return false;
			}
			for (Integer nextBuilding : lists[building]) {
				needs[nextBuilding]--;
			}
			constructed[building]++;
			return true;
		} else {
			if (constructed[building] > 0) {
				for (Integer nextBuilding : lists[building]) {
					needs[nextBuilding]++;
				}
				constructed[building]--;
				return true;
			}
		}
		return false;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
