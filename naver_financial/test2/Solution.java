package naver_financial.test2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static HashMap<Character, Set<Character>> totalKinds = new HashMap<>();

    static class Node {
        int x, y;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[] solution(String[] maps) {
        Set<Character> alphabets = new HashSet<>();
        char[][] originMap = new char[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            final char[] inputs = maps[i].toCharArray();
            for (int j = 0; j < maps[0].length(); j++) {
                originMap[i][j] = inputs[j];
                if (Character.isAlphabetic(inputs[j])) {
                    alphabets.add(inputs[j]);
                    if (!totalKinds.containsKey(inputs[j])) {
                        totalKinds.put(inputs[j], new HashSet<>());
                    }
                }
            }
        }

        int maxCount = 0;
        for (int i = 0; i < originMap.length; i++) {
            for (int j = 0; j < originMap[0].length; j++) {
                if (originMap[i][j] != '.' && alphabets.contains(originMap[i][j])) {
                    char[][] copyMap = copy(originMap);
                    maxCount = Math.max(maxCount, search(i, j, copyMap));
                    alphabets.remove(originMap[i][j]);
                }
            }
        }
        int kindsCount = 0;
        for (Character character : totalKinds.keySet()) {
            kindsCount += totalKinds.get(character).size();
        }
        int[] answer = new int[2];
        answer[0] = kindsCount / 2;
        answer[1] = maxCount;
        return answer;
    }

    private char[][] copy(final char[][] originMap) {
        char[][] copyMap = new char[originMap.length][originMap[0].length];
        for (int i = 0; i < originMap.length; i++) {
            System.arraycopy(originMap[i], 0, copyMap[i], 0, originMap[0].length);
        }
        return copyMap;
    }

    private int search(final int i, final int j, final char[][] copyMap) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[copyMap.length][copyMap[0].length];
        q.add(new Node(i, j));
        visited[i][j] = true;
        char target = copyMap[i][j];
        Set<Character> count = new HashSet<>();

        while (!q.isEmpty()) {
            Node node = q.poll();
            int tx = node.x;
            int ty = node.y;

            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (nx < 0 || nx >= copyMap.length || ny < 0 || ny >= copyMap[0].length) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (copyMap[nx][ny] == '.') {
                    continue;
                }
                if (copyMap[nx][ny] != target) {
                    count.add(copyMap[nx][ny]);
                    final Set<Character> characters = totalKinds.get(target);
                    characters.add(copyMap[nx][ny]);
                    totalKinds.put(target, characters);
                }
                visited[nx][ny] = true;
                q.add(new Node(nx, ny));
            }
        }

        return count.size();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(
                new String[]{"..........", "AAACC.....", ".AAA.....Z", "...BBBBB..", "...ZBBB...", "ZZZZAAAC..",
                        ".....CCCC.", "QQ......C.", ".........."})));
//        System.out.println(Arrays.toString(sol.solution(new String[]{"A.B.C.D", ".B.C.D."})));
    }
}
