package boj.boj1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = stoi(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = stoi(st.nextToken());
            int point = stoi(st.nextToken());
            st = new StringTokenizer(br.readLine());
            PriorityQueue<Integer> weights = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o2, o1);
                }
            });
            Queue<Pair> docs = new LinkedList<>();
            for (int j = 0; j < m; j++) {
                int weight = stoi(st.nextToken());
                weights.offer(weight);
                docs.add(new Pair(j, weight));
            }
            int cnt = 1;
            while (!docs.isEmpty()) {
                Pair tmp = docs.poll();
                if (tmp.weight < weights.peek()) {
                    docs.offer(tmp);
                } else {
                    if (tmp.idx == point) {
                        System.out.println(cnt);
                        break;
                    }
                    weights.poll();
                    cnt++;
                }
            }
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Pair {
    int idx, weight;

    public Pair(int idx, int weight) {
        this.idx = idx;
        this.weight = weight;
    }
}

