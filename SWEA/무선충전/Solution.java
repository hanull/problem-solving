package SWEA.무선충전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1
20 3
2 2 3 2 2 2 2 3 3 4 4 3 2 2 3 3 3 2 2 3
4 4 1 4 4 1 4 4 1 1 1 4 1 4 3 3 3 3 3 3
4 4 1 100
7 10 3 40
6 3 2 70
 */
public class Solution {

    static int M, A;
    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};
    static List<Integer>[][] map = new ArrayList[10][10];
    static int[] routeA, routeB;
    static int[] BC;
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

        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = stoi(st.nextToken());
            A = stoi(st.nextToken());
            routeA = new int[M];
            routeB = new int[M];
            BC = new int[A];
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    map[i][j] = new ArrayList();
                }
            }
            st = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                routeA[i] = stoi(st.nextToken());
                routeB[i] = stoi(st2.nextToken());
            }

            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int y = stoi(st.nextToken()) - 1;
                int x = stoi(st.nextToken()) - 1;
                int range = stoi(st.nextToken());
                int charge = stoi(st.nextToken());
                BC[i] = charge;
                bfs(x, y, range, i);
            }

            int totalCharge = move();
            result.append("#").append(tc).append(" ").append(totalCharge).append("\n");
        }
        System.out.print(result);

    }

    static int move() {
        int total = 0;
        Node userA = new Node(0, 0);
        Node userB = new Node(9, 9);

        int max = 0;
        for (int i = 0; i < map[0][0].size(); i++) {
            max = Math.max(max, BC[map[0][0].get(i)]);
        }
        total += max;
        max = 0;
        for (int i = 0; i < map[9][9].size(); i++) {
            max = Math.max(max, BC[map[9][9].get(i)]);
        }
        total +=  max;

        for (int i = 0; i < M; i++) {
            userA.x = userA.x + dx[routeA[i]];
            userA.y = userA.y + dy[routeA[i]];
            userB.x = userB.x + dx[routeB[i]];
            userB.y = userB.y + dy[routeB[i]];

            int userA_connect_count = map[userA.x][userA.y].size();
            int userB_connect_count = map[userB.x][userB.y].size();

            if (userA_connect_count == 0 && userB_connect_count == 0) {
                continue;
            }
            else if (userA_connect_count != 0 && userB_connect_count == 0) {
                max = 0;
                for (int j = 0; j < map[userA.x][userA.y].size(); j++) {
                    max = Math.max(max, BC[map[userA.x][userA.y].get(j)]);
                }
            } else if (userB_connect_count != 0 && userA_connect_count == 0) {
                max = 0;
                for (int j = 0; j < map[userB.x][userB.y].size(); j++) {
                    max = Math.max(max, BC[map[userB.x][userB.y].get(j)]);
                }
            } else if (userB_connect_count != 0 && userA_connect_count != 0){
                max = 0;
                for (int a = 0; a < userA_connect_count; a++) {
                    for (int b = 0; b < userB_connect_count; b++) {
                        if (map[userA.x][userA.y].get(a) == map[userB.x][userB.y].get(b)) {
                            max = Math.max(max, BC[map[userA.x][userA.y].get(a)]);
                        } else {
                            max = Math.max(max, BC[map[userA.x][userA.y].get(a)] + BC[map[userB.x][userB.y].get(b)]);
                        }
                    }
                }
            }
            total += max;

        }

        return total;
    }

    static void bfs(int x, int y, int range, int no) {
        Deque<Node> deque = new ArrayDeque<>();
        boolean[][] visited = new boolean[10][10];
        visited[x][y] = true;
        deque.add(new Node(x, y));
        map[x][y].add(no);

        for (int i = 0; i < range; i++) {
            int dequeSize = deque.size();
            int cnt = 0;
            while (cnt++ < dequeSize) {
                Node tmp = deque.pollFirst();
                int tx = tmp.x;
                int ty = tmp.y;
                for (int d = 1; d <= 4; d++) {
                    int nx = tx + dx[d];
                    int ny = ty + dy[d];
                    if (!isRange(nx, ny)) continue;
                    if (visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    deque.add(new Node(nx, ny));
                    map[nx][ny].add(no);
                }
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= 10 || y < 0 || y >= 10 ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
