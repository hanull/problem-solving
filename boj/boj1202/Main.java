package boj.boj1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static PriorityQueue<Integer> bagList = new PriorityQueue<>();
    static PriorityQueue<Jewelry> jewelryList, tmpPQ;
    static {
        jewelryList = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        tmpPQ = new PriorityQueue<>((o1, o2) -> o2.price - o1.price);
    }

    static class Jewelry {
        int weight, price;

        public Jewelry(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = stoi(st.nextToken());
            int price = stoi(st.nextToken());
            jewelryList.add(new Jewelry(weight, price));
        }
        for (int i = 0; i < K; i++) {
            bagList.add(stoi(br.readLine()));
        }

        long totalPrice = 0;
        while (!bagList.isEmpty()) {
            int bagWeight = bagList.poll();
            while (!jewelryList.isEmpty() && jewelryList.peek().weight <= bagWeight) {
                tmpPQ.add(jewelryList.poll());
            }
            if (!tmpPQ.isEmpty()) {
                totalPrice += tmpPQ.poll().price;
            }
        }
        System.out.println(totalPrice);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
