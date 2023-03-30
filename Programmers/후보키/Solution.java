package Programmers.후보키;

import java.util.*;

public class Solution {

    static int answer;
    static List<List<Integer>> uniqueKeys = new ArrayList<>();

    public int solution(String[][] relation) {
        for (int size = 1; size <= relation[0].length; size++) {
            comb(0, 0, size, relation, new boolean[relation[0].length]);
        }
        return answer;
    }

    private void comb(final int index, final int count, final int goal, final String[][] relation,
                      final boolean[] used) {
        if (count == goal) {
            List<Integer> usedColumns = getUsedColumns(used);
            if (isUnique(usedColumns, relation) && isMinimum(usedColumns)) {
                answer++;
                uniqueKeys.add(usedColumns);
            }
            return;
        }

        for (int i = index; i < relation[0].length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            comb(i + 1, count + 1, goal, relation, used);
            used[i] = false;
        }
    }

    private static List<Integer> getUsedColumns(final boolean[] used) {
        List<Integer> usedColumns = new ArrayList<>();
        for (int i = 0; i < used.length; i++) {
            if (used[i]) {
                usedColumns.add(i);
            }
        }
        return usedColumns;
    }

    private boolean isUnique(final List<Integer> usedColumns, final String[][] relation) {
        final Set<String> keys = new HashSet<>();
        for (final String[] strings : relation) {
            StringBuilder str = new StringBuilder();
            for (int col : usedColumns) {
                str.append(strings[col]);
            }
            if (keys.contains(str.toString())) {
                return false;
            }
            keys.add(str.toString());
        }
        return keys.size() == relation.length;
    }

    private boolean isMinimum(final List<Integer> usedColumns) {
        if (usedColumns.size() == 1) {
            return true;
        }
        for (List<Integer> uniqueKey : uniqueKeys) {
            if (new HashSet<>(usedColumns).containsAll(uniqueKey)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new String[][]{{"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}}));
    }
}
