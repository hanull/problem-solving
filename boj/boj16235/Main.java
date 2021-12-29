package boj.boj16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

	static int N, treeCount, years;
	static int[][] nutrients, map;
	static PriorityQueue<Tree> trees = new PriorityQueue<>(Comparator.comparingInt(o -> o.age));
	static Queue<Tree> deathTrees = new ArrayDeque<>();
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

	static class Tree {
		int x, y, age;

		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		treeCount = stoi(st.nextToken());
		years = stoi(st.nextToken());
		nutrients = new int[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				nutrients[i][j] = stoi(st.nextToken());
				map[i][j] = 5;
			}
		}
		for (int i = 0; i < treeCount; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken()) - 1;
			int y = stoi(st.nextToken()) - 1;
			int age = stoi(st.nextToken());
			trees.add(new Tree(x, y, age));
		}
		while (years-- > 0) {
			growUpTree();
			changeDeathTreeToNutrient();
			spreadTree();
			addNutrient();
		}
		System.out.println(calculate());
	}

	private static int calculate() {
		return trees.size();
	}

	private static void spreadTree() {
		List<Tree> spreadTrees = getSpreadTrees(trees);
		for (Tree tree : spreadTrees) {
			int x = tree.x;
			int y = tree.y;
			for (int d = 0; d < 8; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (!isRange(nx, ny)) {
					continue;
				}
				trees.add(new Tree(nx, ny, 1));
			}
		}
	}

	private static boolean isRange(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < N;
	}

	private static List<Tree> getSpreadTrees(PriorityQueue<Tree> trees) {
		return trees.stream()
			.filter(tree -> tree.age % 5 == 0)
			.collect(Collectors.toList());
	}

	private static void changeDeathTreeToNutrient() {
		while (!deathTrees.isEmpty()) {
			Tree tree = deathTrees.poll();
			int x = tree.x;
			int y = tree.y;
			int age = tree.age;
			map[x][y] += age / 2;
		}
	}

	private static void growUpTree() {
		Queue<Tree> grownTrees = new ArrayDeque<>();
		while (!trees.isEmpty()) {
			Tree tree = trees.poll();
			int x = tree.x;
			int y = tree.y;
			int age = tree.age;
			if (map[x][y] >= age) {
				map[x][y] -= age;
				grownTrees.add(new Tree(x, y, age + 1));
			} else {
				deathTrees.add(new Tree(x, y, age));
			}
		}
		while (!grownTrees.isEmpty()) {
			trees.add(grownTrees.poll());
		}
	}

	private static void addNutrient() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += nutrients[i][j];
			}
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
