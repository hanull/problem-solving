package boj.boj16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, time;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Shark[] shark = new Shark[1];
    static Queue<Fish> fishList = new LinkedList<>();
    static Fish[] targetFish = new Fish[1];
    static class Shark {
        int x,y,cnt, size;

        public Shark(int x, int y, int cnt, int size) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.size = size;
        }
    }
    static class Fish {
        int x, y, size;

        public Fish(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] > 0 && map[i][j] < 9) fishList.add(new Fish(i, j, map[i][j]));
                if (map[i][j] == 9) {
                    shark[0] = new Shark(i, j, 0, 2);
                    map[i][j] = 0;
                }
            }
        }

        solve();
        System.out.println(time);
    }

    static void solve() {
        while (true) {
            if (fishList.size() == 0) break;
            if (!haveAnyFish()) break;
            if (fishList.size() == 1) {
                targetFish[0] = fishList.poll();
                moveShark();
            } else if (fishList.size() > 1) {
                findNearestFish();
                if (targetFish[0] == null) break;
                moveShark();
            }
        }
    }

    static void moveShark() {
        int dist = getDistance(shark[0].x, shark[0].y, targetFish[0]);
        if (shark[0].size > targetFish[0].size) {
            shark[0].cnt += 1;
            if (shark[0].cnt == shark[0].size) {
                shark[0].size += 1;
                shark[0].cnt = 0;
            }
            shark[0].x = targetFish[0].x;
            shark[0].y = targetFish[0].y;
            map[shark[0].x][shark[0].y] = 0;
        }
        fishList.remove(targetFish[0]);
        time += dist;
    }

    static void findNearestFish() {
        ArrayList<Fish> canEatFishList = new ArrayList<>();
        int sharkSize = shark[0].size;
        int dist = Integer.MAX_VALUE;
        for (Fish fish : fishList) {
            if (sharkSize > fish.size) {    // 먹을 수 있는 물고기가 있을 때
                int d = getDistance(shark[0].x, shark[0].y, fish);
                if (d == Integer.MAX_VALUE) continue;
                if (dist > d) {     // 가장 가까운 물고기를 먹으러 간다.
                    canEatFishList.clear();
                    canEatFishList.add(fish);
                    dist = d;
                } else if (dist == d) {
                    canEatFishList.add(fish);
                }
            }
        }

        // 이동할 수 있는 물고기가 여러마리 일 경우,
        if (canEatFishList.size() > 1) {
            Collections.sort(canEatFishList, new Comparator<Fish>() {
                @Override
                public int compare(Fish o1, Fish o2) {
                    if (o1.x == o2.x) {
                        return Integer.compare(o1.y, o2.y);
                    }
                    return Integer.compare(o1.x, o2.x);
                }
            });
            targetFish[0] = canEatFishList.get(0);

        } else if (canEatFishList.size() == 1) {
            targetFish[0] = canEatFishList.get(0);
        } else {
            targetFish[0] = null;
        }
    }


    static int getDistance(int startX, int startY, Fish fish) {
        Queue<Fish> q = new LinkedList<>();
        q.add(new Fish(startX, startY, 0));
        visited = new boolean[N][N];
        visited[startX][startY] = true;

        int res = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Fish tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int dist = tmp.size;

            if (tx == fish.x && ty == fish.y) {
                res = dist;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (shark[0].size < map[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new Fish(nx, ny, dist + 1));
            }
        }

        return res;
    }

    static boolean haveAnyFish() {
        int x = shark[0].x;
        int y = shark[0].y;
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isRange(nx, ny)) continue;
            if (shark[0].size >= map[nx][ny]) {
                flag = true;
            }
        }
        if (!flag) return false;
        int sharkSize = shark[0].size;
        for (Fish fish : fishList) {
            if (sharkSize > fish.size) return true;
        }
        return false;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
