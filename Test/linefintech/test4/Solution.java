package Test.linefintech.test4;

public class Solution {

	static final int SELECT_COUNT = 2;
	static boolean[] checked;
	static int answer;

	public int solution(int[][] needs, int r) {
		int[] robot = new int[SELECT_COUNT];
		checked = new boolean[needs[0].length];
		selectRobot(0, 0, needs[0].length, robot, needs);
		return answer;
	}

	private void selectRobot(int count, int index, int n, int[] robot, int[][] needs) {
		if (count == SELECT_COUNT) {
			int finishedProductCount = findFinishedProduct(robot, needs, n);
			answer = Math.max(answer, finishedProductCount);
			return;
		}
		for (int i = index; i < n; i++) {
			if (checked[i]) continue;
			checked[i] = true;
			robot[count] = i;
			selectRobot(count + 1, i + 1, n, robot, needs);
			checked[i] = false;
		}
	}

	private int findFinishedProduct(int[] robot, int[][] needs, int n) {
		int finishedProductCount = 0;
		for (int no = 0; no < needs.length; no++) {
			boolean flag = true;
			for (int part = 0; part < n; part++) {
				if (needs[no][part] == 1) {	// 필요한 부품일 경우
					if (!isPossible(part, robot)) {
						flag = false;
						break;
					}
				}
			}
			if (flag) {
				finishedProductCount++;
			}
		}
		return finishedProductCount;
	}

	private boolean isPossible(int part, int[] robot) {
		for (int i = 0; i < robot.length; i++) {
			if (robot[i] == part) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(
			sol.solution(new int[][] {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 1, 1}}, 2));
	}
}
