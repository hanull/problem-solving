package boj.boj23290;

import java.io.*;
import java.util.*;

public class Main {

    static int[][] fishMoveDirection = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static int[][] sharkMoveDirection = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    static class Shark {
        int fishCount;
        String direction;

        public Shark(final int fishCount, final String direction) {
            this.fishCount = fishCount;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[][][] fishCount = new int[4][4][8];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            fishCount[x][y][d]++;
        }
        st = new StringTokenizer(br.readLine());
        int sharkX = Integer.parseInt(st.nextToken()) - 1;
        int sharkY = Integer.parseInt(st.nextToken()) - 1;
        int[][] fishSmell = new int[4][4];
        for (int i = 0; i < 4; i++) {
            Arrays.fill(fishSmell[i], Integer.MAX_VALUE);
        }

        int time = 0;
        while (time++ < S) {
            // 현재 물고기 복사
            int[][][] copyFishCount = new int[4][4][8];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.arraycopy(fishCount[i][j], 0, copyFishCount[i][j], 0, 8);
                }
            }

            // 물고기 이동
            moveFish(sharkX, sharkY, fishSmell, fishCount);

            // 상어 3칸 이동
            PriorityQueue<Shark> nextSharks = findAllNextShark(fishCount, sharkX, sharkY);
            if (!nextSharks.isEmpty()) {
                Shark shark = nextSharks.poll();
                for (int i = 0; i < 3; i++) {
                    int direction = shark.direction.charAt(i) - '0';
                    sharkX += sharkMoveDirection[direction][0];
                    sharkY += sharkMoveDirection[direction][1];

                    // 해당 위치 물고기 먹기
                    // 물고기 냄새 추가
                    eatFish(sharkX, sharkY, fishCount, fishSmell, time);
                }
            }

            // 2번 전에 생긴 냄새 제거
            removeFishSmell(fishSmell, time);

            // 물고기 복사
            copyAllOfOriginFish(fishCount, copyFishCount);
        }

        int answer = getTotalFish(fishCount);
        System.out.println(answer);
    }

    private static void moveFish(final int sharkX, final int sharkY, final int[][] fishSmell,
                                 final int[][][] fishCount) {
        int[][][] newFishCount = new int[4][4][8];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d = 0; d < 8; d++) {
                    if (fishCount[i][j][d] > 0) {
                        int currentDirection = d;
                        for (int turnCount = 0; turnCount < 8; turnCount++) {
                            int nx = i + fishMoveDirection[currentDirection][0];
                            int ny = j + fishMoveDirection[currentDirection][1];

                            // 범위를 벗어난 경우, 상어가 존재하는 경우, 물고기 냄새가 있는 경우
                            if (isOutOfRange(nx, ny) || nx == sharkX && ny == sharkY
                                    || fishSmell[nx][ny] != Integer.MAX_VALUE) {
                                currentDirection = turn(currentDirection);
                                continue;
                            }

                            // 물고기 이동
                            // 이전 위치 물고기 수 제거
                            // 새로운 위치 물고기 수 추가
                            int moveCount = fishCount[i][j][d];
                            fishCount[i][j][d] = 0;
                            newFishCount[nx][ny][currentDirection] += moveCount;
                            break;
                        }
                    }
                }
            }
        }
        // 물고기 위치 및 수 업데이트
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d = 0; d < 8; d++) {
                    fishCount[i][j][d] += newFishCount[i][j][d];
                }
            }
        }
    }

    private static boolean isOutOfRange(final int x, final int y) {
        return x < 0 || x >= 4 || y < 0 || y >= 4;
    }

    private static int turn(final int currentDirection) {
        if (currentDirection == 0) {
            return 7;
        }
        return currentDirection - 1;
    }

    private static PriorityQueue<Shark> findAllNextShark(final int[][][] fishCount, final int sharkX,
                                                         final int sharkY) {
        PriorityQueue<Shark> nextSharkPositions = new PriorityQueue<>((o1, o2) -> {
            if (o1.fishCount == o2.fishCount) {
                return o1.direction.compareTo(o2.direction);
            }
            return o2.fishCount - o1.fishCount;
        });

        dfs(0, 0, sharkX, sharkY, "", nextSharkPositions, copyFishCount(fishCount));

        return nextSharkPositions;
    }

    private static void dfs(final int moveCount, final int ateFishCount, final int x, final int y,
                            final String direction, final PriorityQueue<Shark> nextSharkPositions,
                            final int[][][] fishCount) {
        if (moveCount == 3) {
            nextSharkPositions.add(new Shark(ateFishCount, direction));
            return;
        }

        for (int nextDirection = 0; nextDirection < 4; nextDirection++) {
            int nx = x + sharkMoveDirection[nextDirection][0];
            int ny = y + sharkMoveDirection[nextDirection][1];
            if (isOutOfRange(nx, ny)) {
                continue;
            }
            int[] counts = new int[8];
            int totalCount = 0;
            for (int i = 0; i < 8; i++) {
                counts[i] = fishCount[nx][ny][i];
                totalCount += counts[i];
                fishCount[nx][ny][i] = 0;
            }
            dfs(moveCount + 1, ateFishCount + totalCount, nx, ny, direction + nextDirection,
                    nextSharkPositions, fishCount);
            System.arraycopy(counts, 0, fishCount[nx][ny], 0, 8);
        }
    }

    private static int[][][] copyFishCount(final int[][][] originFishCount) {
        int[][][] fishCount = new int[4][4][8];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.arraycopy(originFishCount[i][j], 0, fishCount[i][j], 0, 8);
            }
        }
        return fishCount;
    }

    private static void eatFish(final int x, final int y, final int[][][] fishCount, final int[][] fishSmell,
                                final int time) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d = 0; d < 8; d++) {
                    if (fishCount[x][y][d] > 0) {
                        fishSmell[x][y] = time;
                        fishCount[x][y][d] = 0;
                    }
                }
            }
        }
    }

    private static void removeFishSmell(final int[][] fishSmell, final int time) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (fishSmell[i][j] <= time - 2) {
                    fishSmell[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    private static void copyAllOfOriginFish(final int[][][] fishCount, final int[][][] copyFishCount) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d = 0; d < 8; d++) {
                    fishCount[i][j][d] += copyFishCount[i][j][d];
                }
            }
        }
    }

    private static int getTotalFish(final int[][][] fishCount) {
        int answer = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d = 0; d < 8; d++) {
                    answer += fishCount[i][j][d];
                }
            }
        }
        return answer;
    }
}
