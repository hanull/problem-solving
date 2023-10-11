package boj.boj12100;

import java.io.*;
import java.util.*;

public class Main {

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, map);
        System.out.println(answer);
    }

    private static void dfs(final int count, final int[][] map) {
        if (count == 5) {
            answer = Math.max(answer, findMaxValue(map));
            return;
        }

        int[][] tempMap = copyMap(map);
        for (int d = 0; d < 4; d++) {
            merge(d, map);
            dfs(count + 1, map);
            for (int i = 0; i < map.length; i++) {
                System.arraycopy(tempMap[i], 0, map[i], 0, map.length);
            }
        }
    }

    private static int findMaxValue(final int[][] map) {
        int max = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }

    private static int[][] copyMap(final int[][] originMap) {
        int N = originMap.length;
        int[][] copyMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(originMap[i], 0, copyMap[i], 0, N);
        }
        return copyMap;
    }

    private static void merge(final int direction, final int[][] map) {
        if (direction == 0) {
            for (int col = 0; col < map.length; col++) {
                List<Integer> nodes = new ArrayList<>();
                for (final int[] nums : map) {
                    if (nums[col] > 0) {
                        nodes.add(nums[col]);
                        nums[col] = 0;
                    }
                }

                int index = 0;
                for (int i = 0; i < nodes.size(); i++) {
                    if (i == nodes.size() - 1) {
                        map[index][col] = nodes.get(i);
                    } else {
                        if (nodes.get(i).equals(nodes.get(i + 1))) {
                            map[index++][col] = nodes.get(i) * 2;
                            i++;
                        } else {
                            map[index++][col] = nodes.get(i);
                        }
                    }
                }
            }
        }

        if (direction == 1) {
            for (int col = 0; col < map.length; col++) {
                List<Integer> nodes = new ArrayList<>();
                for (int row = map.length - 1; row >= 0; row--) {
                    if (map[row][col] > 0) {
                        nodes.add(map[row][col]);
                        map[row][col] = 0;
                    }
                }

                int index = map.length - 1;
                for (int i = 0; i < nodes.size(); i++) {
                    if (i == nodes.size() - 1) {
                        map[index][col] = nodes.get(i);
                    } else {
                        if (nodes.get(i).equals(nodes.get(i + 1))) {
                            map[index--][col] = nodes.get(i) * 2;
                            i++;
                        } else {
                            map[index--][col] = nodes.get(i);
                        }
                    }
                }
            }
        }

        if (direction == 2) {
            for (int row = 0; row < map.length; row++) {
                List<Integer> nodes = new ArrayList<>();
                for (int col = 0; col < map.length; col++) {
                    if (map[row][col] > 0) {
                        nodes.add(map[row][col]);
                        map[row][col] = 0;
                    }
                }

                int index = 0;
                for (int i = 0; i < nodes.size(); i++) {
                    if (i == nodes.size() - 1) {
                        map[row][index] = nodes.get(i);
                    } else {
                        if (nodes.get(i).equals(nodes.get(i + 1))) {
                            map[row][index++] = nodes.get(i) * 2;
                            i++;
                        } else {
                            map[row][index++] = nodes.get(i);
                        }
                    }
                }
            }
        }

        if (direction == 3) {
            for (int row = 0; row < map.length; row++) {
                List<Integer> nodes = new ArrayList<>();
                for (int col = map.length - 1; col >= 0; col--) {
                    if (map[row][col] > 0) {
                        nodes.add(map[row][col]);
                        map[row][col] = 0;
                    }
                }

                int index = map.length - 1;
                for (int i = 0; i < nodes.size(); i++) {
                    if (i == nodes.size() - 1) {
                        map[row][index] = nodes.get(i);
                    } else {
                        if (nodes.get(i).equals(nodes.get(i + 1))) {
                            map[row][index--] = nodes.get(i) * 2;
                            i++;
                        } else {
                            map[row][index--] = nodes.get(i);
                        }
                    }
                }
            }
        }
    }
}
