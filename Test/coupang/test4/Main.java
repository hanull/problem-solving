package Test.coupang.test4;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Solution sol = new Solution();
        String depar = "S";
        String hub = "DG";
        String dest = "Y";
        String[][] roads = {
                {"U", "B"},
                {"DJ", "U"},
                {"DJ", "G"},
                {"S","DJ"},
                {"S", "U"},
                {"DJ", "DG"},
                {"G","B"},
                {"DG","G"},
                {"DG","B"},
                {"U","DG"},
                {"G","Y"},
                {"B","Y"}
        };
        System.out.println(sol.solution(depar, hub, dest, roads));
    }
}

class Solution {
    static int cnt = 0;
    public int solution(String depar, String hub, String dest, String[][] roads) {
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        for (int i=0; i<roads.length; i++) {
            for (int j=0; j<2; j++) {
                if (!hashMap.containsKey(roads[i][0])) {
                    hashMap.put(roads[i][0], new ArrayList<>());
                }
                ArrayList<String> tmp = hashMap.get(roads[i][0]);
                if (!tmp.contains(roads[i][1])) {
                    tmp.add(roads[i][1]);
                }
                hashMap.put(roads[i][0], tmp);
            }
        }
        for (String start : hashMap.get(depar)) {
            dfs(hashMap, start, hub, dest, false);
        }
        return cnt;
    }

    private void dfs(HashMap<String, ArrayList<String>> hashMap, String cur, String hub, String dest, boolean flag) {
        if (cur.equals(dest)) {
            if (flag) cnt++;
            return;
        } else if (cur.equals(hub)) {
            for (String tmp : hashMap.get(hub)) {
                dfs(hashMap, tmp, hub, dest, true);
            }
            return;
        } else if (!hashMap.containsKey(cur)){
            return;
        } else {
            for (String tmp : hashMap.get(cur)) {
                dfs(hashMap, tmp, hub, dest, flag);
            }
            return;
        }
    }

}