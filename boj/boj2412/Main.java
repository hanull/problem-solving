package boj.boj2412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static List<Integer>[] lists;
    static class Node {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        T = stoi(st.nextToken());
        lists = new List[T + 1];
        for (int i = 0; i <= T; i++) lists[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = stoi(st.nextToken());
            int x = stoi(st.nextToken());
            lists[x].add(y);
        }
        for (int i = 0; i <= T; i++) Collections.sort(lists[i]);
        System.out.println(bfs());
    }

    private static int bfs() {
        int answer = -1;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            int tx = node.x;
            int ty = node.y;
            int dist = node.dist;
            if (tx == T) {
                answer = dist;
                break;
            }
            for (int nx = tx - 2; nx <= tx + 2; nx++) {
                if (nx < 0 || nx > T) continue;
                for (int j=0; j<lists[nx].size(); j++) {
                    int ny = lists[nx].get(j);
                    if (Math.abs(ny - ty) <= 2) {
                        q.add(new Node(nx, ny, dist + 1));
                        lists[nx].remove(j);
                        j--;
                    }
                }
            }
        }
        return answer;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
