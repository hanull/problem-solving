package SWEA.swea1208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

    static int dumpCount;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PriorityQueue<Integer> higherBox = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    static PriorityQueue<Integer> lowerBox = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= 10; tc++) {
            init();
            for (int i = 0; i < dumpCount; i++) {
                dump();
            }
            int res = higherBox.poll() - lowerBox.poll();
            System.out.println("#" + tc + " " + res);
            higherBox.clear();
            lowerBox.clear();
        }
    }

    static void dump() {
        int lower = lowerBox.poll() + 1;
        int higher = higherBox.poll() - 1;
        lowerBox.add(lower);
        higherBox.add(higher);
    }

    static void init() throws IOException {
        dumpCount = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 100; i++) {
            int num = stoi(st.nextToken());
            lowerBox.add(num);
            higherBox.add(num);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
