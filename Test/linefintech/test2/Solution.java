package Test.linefintech.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public int solution(int[] x, int[] y) {
		List<Node> lists = new ArrayList<>();
		for (int i = 0; i < x.length; i++) {
			lists.add(new Node(x[i], y[i]));
		}
		Collections.sort(lists, (o1, o2) -> {
			if (o1.x == o2.x) {
				return o1.y - o2.y;
			}
			return o1.x - o2.x;
		});
		int answer = 0;
		for (int i = 0; i < lists.size() - 1; i++) {
			int distance = (int)Math.ceil(getDistance(lists.get(i), lists.get(i + 1)));
			answer = Math.max(answer, distance);
		}
		return answer;
	}

	private double getDistance(Node leftNode, Node rightNode) {
		int x = (int)Math.pow(rightNode.x - leftNode.x, 2);
		int y = (int)Math.pow(rightNode.y - leftNode.y, 2);
		return Math.sqrt(x + y);
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution(new int[] {1, 2, 6, 8}, new int[] {1, 2, 5, 7}));
		System.out.println(sol.solution(new int[] {1, 2, 4, 2}, new int[] {1, 1, 4, 2}));
	}
}
