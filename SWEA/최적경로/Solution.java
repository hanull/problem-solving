package SWEA.최적경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, dist;
    static int[] isSelectedOrder;
    static boolean[] visited;
    static Node[] customerList;
    static Node home, company;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = stoi(br.readLine());
            customerList = new Node[N];
            isSelectedOrder = new int[N];
            visited = new boolean[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            home = new Node(stoi(st.nextToken()), stoi(st.nextToken()));
            company = new Node((stoi(st.nextToken())), stoi(st.nextToken()));

            for (int i = 0; i < N; i++) {
                customerList[i] = new Node((stoi(st.nextToken())), stoi(st.nextToken()));
            }

            dist = 100 * N;
            selectOrderOfDelivery(0);

            sb.append("#" + tc + " " + dist + "\n");
        }
        System.out.print(sb);
    }

//    static void selectOrderOfDelivery(int cnt) {
//        if (cnt == N) {
//            dist = Math.min(dist, calcDistance());
//            return;
//        }
//
//        for (int i = 0; i < N; i++) {
//            if (visited[i]) continue;
//            visited[i] = true;
//            isSelectedOrder[cnt] = i;
//            selectOrderOfDelivery(cnt + 1);
//            visited[i] = false;
//        }
//    }

    static void selectOrderOfDelivery(int cnt) {
        for (int i = 0; i < N; i++) isSelectedOrder[i] = i;
        do {
            dist = Math.min(dist, calcDistance());
        } while (nextPermutation());
    }

    static boolean nextPermutation() {
        int i = N - 1;
        while (i > 0 && isSelectedOrder[i-1] >= isSelectedOrder[i]) i--;

        if (i == 0) return false;

        int j = N - 1;
        while (isSelectedOrder[i-1] >= isSelectedOrder[j]) j--;

        swap(i - 1, j);

        j = N - 1;
        while (i < j) {
            swap(i++, j--);
        }

        return true;
    }

    static void swap(int i, int j) {
        int temp = isSelectedOrder[i];
        isSelectedOrder[i] = isSelectedOrder[j];
        isSelectedOrder[j] = temp;
    }

    static int calcDistance() {
        // 회사 -> 첫 번째 고객
        int totalDist = Math.abs(company.x - customerList[isSelectedOrder[0]].x) + Math.abs(company.y - customerList[isSelectedOrder[0]].y);

        int x = customerList[isSelectedOrder[0]].x;
        int y = customerList[isSelectedOrder[0]].y;
        for (int i = 1; i < N; i++) {
            int nx = customerList[isSelectedOrder[i]].x;
            int ny = customerList[isSelectedOrder[i]].y;
            totalDist += Math.abs(x - nx) + Math.abs(y - ny);
            if (totalDist > dist) return totalDist;
            x = nx;
            y = ny;
        }
        totalDist += Math.abs(x - home.x) + Math.abs(y - home.y);
        return totalDist;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
