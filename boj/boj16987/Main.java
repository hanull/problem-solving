package boj.boj16987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static Node[] eggs;
    static class Node {
        int hp, weight;

        public Node(int hp, int weight) {
            this.hp = hp;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        eggs = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int hp = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            eggs[i] = new Node(hp, weight);
        }
        if (N == 1) {
            System.out.println(0);
        } else {
            dfs(0, 0);
            System.out.println(answer);
        }
    }

    private static void dfs(int index, int total) {
        answer = Math.max(answer, total);
        if (index == N) return;

        if (eggs[index].hp <= 0 || !isPossible()) {
            dfs(index + 1, total);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (index == i) continue;
            if (eggs[i].hp <= 0) continue;
            int leftWeight = eggs[index].weight;
            int rightWeight = eggs[i].weight;
            eggs[i].hp -= leftWeight;
            eggs[index].hp -= rightWeight;
            int broken = 0;
            if (eggs[i].hp <= 0) broken++;
            if (eggs[index].hp <= 0) broken++;
            dfs(index + 1, total + broken);
            eggs[i].hp += leftWeight;
            eggs[index].hp += rightWeight;
        }
    }

    private static boolean isPossible() {
        for (Node egg : eggs) {
            if (egg.hp > 0) return true;
        }
        return false;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
