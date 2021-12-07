package boj.boj14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main3 {

	static int[][] wheels = new int[4][8];
	static boolean[] check = new boolean[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			String input = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheels[i][j] = Character.getNumericValue(input.charAt(j));
			}
		}
		int K = stoi(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int no = stoi(st.nextToken()) - 1;
			int direction = stoi(st.nextToken());
			run(no, direction);
		}
		System.out.println(calculate());
	}

	private static int calculate() {
		int answer = 0;
		int[] score = {1, 2, 4, 8};
		for (int i = 0; i < 4; i++) {
			if (wheels[i][0] == 1) {
				answer += score[i];
			}
		}
		return answer;
	}

	private static void run(int no, int direction) {
		check = checkPossibleToTurn();
		turn(no, direction);
		int oppositeDirection = direction * -1;
		if (no == 0) {
			turnRightWheels(1, oppositeDirection);
		} else if (no == 3) {
			turnLeftWheels(2, oppositeDirection);
		} else if (no == 1) {
			turnLeftWheels(0, oppositeDirection);
			turnRightWheels(2, oppositeDirection);
		} else {
			turnLeftWheels(1, oppositeDirection);
			turnRightWheels(3, oppositeDirection);
		}
	}

	private static void turnLeftWheels(int no, int direction) {
		int d = direction;
		for (int i = no; i >= 0; i--) {
			if (!check[i])
				break;
			turn(i, d);
			d *= -1;
		}
	}

	private static void turnRightWheels(int no, int direction) {
		int d = direction;
		for (int i = no; i < 4; i++) {
			if (!check[i - 1])
				break;
			turn(i, d);
			d *= -1;
		}
	}

	private static void turn(int no, int direction) {
		if (direction == 1) {    // 시계 방향
			int temp = wheels[no][7];
			for (int i = 7; i >= 1; i--) {
				wheels[no][i] = wheels[no][i - 1];
			}
			wheels[no][0] = temp;
		} else {    // 반시계 방향
			int temp = wheels[no][0];
			for (int i = 0; i <= 6; i++) {
				wheels[no][i] = wheels[no][i + 1];
			}
			wheels[no][7] = temp;
		}
	}

	private static boolean[] checkPossibleToTurn() {
		Arrays.fill(check, false);
		if (wheels[0][2] != wheels[1][6])
			check[0] = true;
		if (wheels[1][2] != wheels[2][6])
			check[1] = true;
		if (wheels[2][2] != wheels[3][6])
			check[2] = true;
		return check;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
