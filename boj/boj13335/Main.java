package boj.boj13335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Truck {
		int weight, point;

		public Truck(int weight, int point) {
			this.weight = weight;
			this.point = point;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		int bridgeLength = stoi(st.nextToken());
		int maxWeight = stoi(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Queue<Truck> trucks = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			trucks.add(new Truck(stoi(st.nextToken()), bridgeLength));
		}
		int currentBridgeWeight = 0;
		int time = 0;
		List<Truck> bridgeTrucks = new ArrayList<>();
		while (true) {
			if (bridgeTrucks.size() == 0 && trucks.isEmpty()) {
				break;
			}
			time++;
			// move
			for (Truck bridgeTruck : bridgeTrucks) {
				bridgeTruck.point--;
			}
			if (bridgeTrucks.size() > 0 && bridgeTrucks.get(0).point == 0) {
				currentBridgeWeight -= bridgeTrucks.get(0).weight;
				bridgeTrucks.remove(0);
			}
			if (!trucks.isEmpty() && currentBridgeWeight + trucks.peek().weight <= maxWeight) {
				currentBridgeWeight += trucks.peek().weight;
				bridgeTrucks.add(trucks.poll());
			}
		}
		System.out.println(time);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
