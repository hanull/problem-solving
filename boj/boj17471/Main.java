package boj.boj17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] vertex, selected;
    static int[] redArea, blueArea;
    static List<Integer>[] adjMatrix;
    static int totalPopulation;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        vertex = new int[N + 1];
        adjMatrix = new ArrayList[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            vertex[i] = stoi(st.nextToken());
            adjMatrix[i] = new ArrayList<>();
            totalPopulation += vertex[i];
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int count = stoi(st.nextToken());
            for (int j = 0; j < count; j++) {
                adjMatrix[i].add(stoi(st.nextToken()));
            }
        }

        // power set : 1 ~ N-1 개의 선거구 선택
        for (int selectCount = 1; selectCount < N; selectCount++) {
            selected = new int[selectCount];
            selectVertex(0, 1, selectCount);
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void selectVertex(int cnt, int start, int goal) {
        if (cnt == goal) {
            // 각각의 선거구가 연결되어 있는지 확인
            boolean[] checked = new boolean[N + 1];
            redArea = new int[goal];
            blueArea = new int[N - goal];

            int idx = 0;
            for (int n : selected) {
                redArea[idx++] = n;
                checked[n] = true;
            }
            idx = 0;
            for (int i = 1; i <= N; i++) {
                if (!checked[i]) blueArea[idx++] = i;
            }

            boolean redFlag = isConnect(redArea, new boolean[N + 1]);
            boolean blueFlag = isConnect(blueArea, new boolean[N + 1]);
            if (!redFlag || !blueFlag) return;

            int redPopulation = calculatePopulation(redArea);
            int bluePopulation = totalPopulation - redPopulation;
            min = Math.min(min, Math.abs(redPopulation - bluePopulation));
            return;
        }

        for (int i = start; i <= N; i++) {
            selected[cnt] = i;
            selectVertex(cnt + 1, i + 1, goal);
        }
    }

    static boolean isConnect(int[] area, boolean[] visited) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(area[0]);
        visited[area[0]] = true;
        int count = 0;
        while (!deque.isEmpty()) {
            int current = deque.poll();
            count++;
            if (area.length == count) break;
            for (int next : adjMatrix[current]) {
                if (visited[next] || !isContain(area, next)) continue;
                visited[next] = true;
                deque.add(next);
            }
        }
        for (int v : area) {
            if (!visited[v]) return false;
        }
        return true;
    }

    static boolean isContain(int[] area, int next) {
        for (int v : area) {
            if (v == next) return true;
        }
        return false;
    }
    static int calculatePopulation(int[] redArea) {
        int result = 0;
        for (int v : redArea) {
            result += vertex[v];
        }
        return result;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
