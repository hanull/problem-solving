package boj.boj11497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] numbers, tmp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		int T = stoi(br.readLine());
		while (T-- > 0) {
			N = stoi(br.readLine());
			numbers = new int[N];
			tmp = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = stoi(st.nextToken());
			}
			Arrays.sort(numbers);
			if (numbers[0] == numbers[N - 1]) {
				answer.append(0).append("\n");
				continue;
			}
			int index = 0;
			int left = 0;
			int right = N - 1;
			while (left <= right) {
				tmp[left++] = numbers[index++];
				if (index < N) {
					tmp[right--] = numbers[index++];
				}

			}
			answer.append(calculate()).append("\n");
		}
		System.out.print(answer);
	}

	private static int calculate() {
		int answer = tmp[N - 1] - tmp[0];
		for (int i = 1; i < N; i++) {
			answer = Math.max(answer, Math.abs(tmp[i] - tmp[i - 1]));
		}
		return answer;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
