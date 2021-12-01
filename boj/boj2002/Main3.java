package boj.boj2002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
4
a
b
c
d
d
a
c
b

answer : 2
 */

public class Main3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashMap<String, Integer> cars = new HashMap<>();
		for (int i = 0; i < N; i++) {
			cars.put(br.readLine(), i);
		}
		int[] arrivedCars = new int[N];
		for (int i = 0; i < N; i++) {
			arrivedCars[i] = cars.get(br.readLine());
		}
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (arrivedCars[i] > arrivedCars[j]) {
					answer++;
					break;
				}
			}
		}
		System.out.println(answer);
	}
}
