package Programmers.자물쇠와열쇠;

import java.util.*;

public class Solution {

    public boolean solution(int[][] key, int[][] lock) {
        int keyLen = key.length;
        int lockLen = lock.length;
        int mapLen = lockLen + (keyLen - 1) * 2;
        int[][] map = new int[mapLen][mapLen];
        int a = 0;
        for (int i = keyLen - 1; i < keyLen + lockLen - 1; i++) {
            int b = 0;
            for (int j = keyLen - 1; j < keyLen + lockLen - 1; j++) {
                map[i][j] = lock[a][b++];
            }
            a++;
        }

        for (int t = 0; t < 4; t++) {
            if (isCheck(key, keyLen, lockLen, map)) {
                return true;
            }
            key = turn(key, keyLen);
        }
        return false;
    }

    private boolean isCheck(final int[][] key, final int keyLen, final int lockLen, final int[][] map) {
        int mapLen = map.length;
        for (int i = 0; i <= mapLen - keyLen; i++) {
            for (int j = 0; j <= mapLen - keyLen; j++) {
                // map에 key를 더한다.
                for (int a = 0; a < keyLen; a++) {
                    for (int b = 0; b < keyLen; b++) {
                        map[i + a][j + b] += key[a][b];
                    }
                }

                // 자물쇠 부분이 모두 1인지 확인
                boolean flag = true;
                for (int a = keyLen - 1; a < keyLen + lockLen - 1; a++) {
                    for (int b = keyLen - 1; b < keyLen + lockLen - 1; b++) {
                        if (map[a][b] != 1) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) {
                        break;
                    }
                }

                // 모두 1이라면, true
                if (flag) {
                    return true;
                }

                // 아니라면, map 원상 복구 및 false
                for (int a = 0; a < keyLen; a++) {
                    for (int b = 0; b < keyLen; b++) {
                        map[i + a][j + b] -= key[a][b];
                    }
                }
            }
        }

        return false;
    }

    private int[][] turn(int[][] key, int m) {
        int[][] newKey = new int[m][m];
        for (int i = 0; i < m; i++) {
            Queue<Integer> q = new ArrayDeque<>();
            for (int j = 0; j < m; j++) {
                q.add(key[i][j]);
            }
            for (int j = 0; j < m; j++) {
                newKey[j][m - 1 - i] = q.poll();
            }
        }
        return newKey;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}},
                new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}));
    }
}
