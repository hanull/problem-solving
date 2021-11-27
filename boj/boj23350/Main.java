package boj.boj23350;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static class Container {
		int priority, weight;

		public Container(int priority, int weight) {
			this.priority = priority;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		Deque<Container> rails = new ArrayDeque<>();
		int[] count = new int[M + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int priority = stoi(st.nextToken());
			int weight = stoi(st.nextToken());
			count[priority]++;
			rails.addLast(new Container(priority, weight));
		}
		int targetPriority = M;
		int answer = 0;
		Stack<Container> stack = new Stack<>();	// 적재하는 저장소
		while (!rails.isEmpty()) {
			Container container = rails.pollFirst();
			int priority = container.priority;
			int weight = container.weight;
			if (isLowestPriority(count, priority)) {	// 낮은 우선순위의 컨테이너가 남아있을 경우
				rails.addLast(new Container(priority, weight));
				answer += weight;
				continue;
			}

			answer += weight;

			// 무거운 무게의 컨테이너가 가장 아래로 이동
			Stack<Container> tempStack = new Stack<>();
			while (!stack.isEmpty() && stack.peek().weight < weight && stack.peek().priority == targetPriority) {
				Container tempContainer = stack.pop();
				answer += tempContainer.weight;
				tempStack.add(tempContainer);
			}
			stack.add(new Container(priority, weight));
			while (!tempStack.isEmpty()) {
				Container tempContainer = tempStack.pop();
				answer += tempContainer.weight;
				stack.add(tempContainer);
			}

			count[targetPriority]--;
			if (count[targetPriority] == 0){
				targetPriority--;
			}

		}
		System.out.println(answer);
	}

	private static boolean isLowestPriority(int[] count, int priority) {
		for (int i = M; i > priority; i--) {
			if (count[i] > 0)	// 더 낮은 우선순위가 존재할 경우
				return true;
		}
		return false;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
