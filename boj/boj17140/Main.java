package boj.boj17140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static final int END_TIME = 100;
    static int r, c, targetNumber;
    static class Node implements Comparable<Node> {
        int number, count;

        public Node(int number, int count) {
            this.number = number;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            if (count == o.count) {
                return number - o.number;
            }
            return count - o.count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        r = stoi(st.nextToken()) - 1;
        c = stoi(st.nextToken()) - 1;
        targetNumber = stoi(st.nextToken());
        arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = stoi(st.nextToken());
            }
        }

        int time = 0;
        while (true) {
            if (time > END_TIME) {
                time = -1;
                break;
            }
            int rowLength = arr.length;
            int colLength = arr[0].length;
            if (r < rowLength && c < colLength && arr[r][c] == targetNumber) {
                break;
            }
            if (rowLength >= colLength) {
                calculateByRow(rowLength, colLength);
            } else {
                calculateByCol(rowLength, colLength);
            }
            time++;
        }
        System.out.println(time);
    }

    private static void calculateByCol(int rowLength, int colLength) {
        List[] lists = new List[colLength];
        for (int i = 0; i < colLength; i++) {
            lists[i] = new ArrayList<Node>();
        }
        int maxRowLength = 3;
        for (int i = 0; i < colLength; i++) {
            Map<Integer, Integer> map = initializeMap();
            for (int j = 0; j < rowLength; j++) {
                if (arr[j][i] == 0) {
                    continue;
                }
                map.compute(arr[j][i], (k, v) -> v + 1);
            }
            for (Integer key : map.keySet()) {
                if (map.get(key) == 0) {
                    continue;
                }
                lists[i].add(new Node(key, map.get(key)));
            }
            Collections.sort(lists[i]);
            maxRowLength = Math.max(maxRowLength, lists[i].size() * 2);
        }
        arr = new int[maxRowLength][colLength];
        for (int i = 0; i < colLength; i++) {
            for (int j = 0; j < lists[i].size() * 2; j += 2) {
                Node node = (Node) lists[i].get(j / 2);
                arr[j][i] = node.number;
                arr[j + 1][i] = node.count;
            }
        }
    }

    private static void calculateByRow(int rowLength, int colLength) {
        List[] lists = new List[rowLength];
        for (int i = 0; i < rowLength; i++) {
            lists[i] = new ArrayList<Node>();
        }
        int maxColLength = 3;
        for (int i = 0; i < rowLength; i++) {
            Map<Integer, Integer> map = initializeMap();
            for (int j = 0; j < colLength; j++) {
                if (arr[i][j] == 0) {
                    continue;
                }
                map.compute(arr[i][j], (k, v) -> v + 1);
            }
            for (Integer key : map.keySet()) {
                if (map.get(key) == 0) {
                    continue;
                }
                lists[i].add(new Node(key, map.get(key)));
            }
            Collections.sort(lists[i]);
            maxColLength = Math.max(maxColLength, lists[i].size() * 2);
        }
        arr = new int[rowLength][maxColLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < lists[i].size() * 2; j += 2) {
                Node node = (Node) lists[i].get(j / 2);
                arr[i][j] = node.number;
                arr[i][j + 1] = node.count;
            }
        }
    }

    private static Map<Integer, Integer> initializeMap() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            map.put(i, 0);
        }
        return map;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
