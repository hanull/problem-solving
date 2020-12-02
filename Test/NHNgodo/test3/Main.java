package Test.NHNgodo.test3;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        String s = "aaaabbbbc";
        int n = 5;
        Solution sol = new Solution();
        System.out.println(sol.solution(s, n));
    }
}

//핵심 소스코드의 설명을 주석으로 작성하면 평가에 큰 도움이 됩니다.
class Solution{

    static int min;

    public int solution(String s, int n){
        int[] alpa = new int['z' - 'a' + 1];
        for (int i = 0; i < s.length(); i++) {
            alpa[s.charAt(i) - 'a'] += 1;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < alpa.length; i++) {
            if (alpa[i] > 0) list.add(alpa[i]);
        }
        Collections.sort(list);
        min = list.get(list.size() - 1) - list.get(0);
        for (int i = 2; i <= n; i++) {
            dfs(1, i);
        }
        return min;
    }

    private void dfs(int cnt, int goal) {
        if (cnt == goal) {

            return;
        }

    }
}