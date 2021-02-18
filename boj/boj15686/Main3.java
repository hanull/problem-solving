package boj.boj15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main3 {

    static int N, M;
    static int[][] map;
    static ArrayList<Node> allChickenJointList = new ArrayList<>();
    static ArrayList<Node> houseList = new ArrayList<>();
    static Node[] selectedChickenJoint;
    static int min = Integer.MAX_VALUE;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] == 2) allChickenJointList.add(new Node(i, j));
                if (map[i][j] == 1) houseList.add(new Node(i, j));
            }
        }

        for (int m = 1; m <= M; m++) {
            selectedChickenJoint = new Node[m];
            pickChickenJoint(0, 0, m);
        }
        System.out.println(min);

    }

    static void pickChickenJoint(int cnt, int start, int goal) {
        if (cnt == goal) {
            min = Math.min(min, calcCityDistance());
            return;
        }

        for (int i = start; i < allChickenJointList.size(); i++) {
            selectedChickenJoint[cnt] = allChickenJointList.get(i);
            pickChickenJoint(cnt + 1, i + 1, goal);
        }
    }

    static int calcCityDistance() {
        int cityDistance = 0;
        for (Node house : houseList) {
            int x = house.x;
            int y = house.y;
            int chickenDistance = N * N;
            for (Node chickenJoint : selectedChickenJoint) {
                int d = Math.abs(x - chickenJoint.x) + Math.abs(y - chickenJoint.y);
                chickenDistance = Math.min(chickenDistance, d);
            }
            cityDistance += chickenDistance;
        }
        return cityDistance;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
