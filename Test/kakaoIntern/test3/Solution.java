package Test.kakaoIntern.test3;

import java.util.*;

/*
정확성 100
효율성 50
 */
public class Solution {

    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();
        boolean[] flag = new boolean[n];
        Arrays.fill(flag, true);
        Stack<Integer> remove = new Stack<>();
        int cursor = k;
        for (int i = 0; i < cmd.length; i++) {
            String command = cmd[i];
            if (command.equals("C")) {
                flag[cursor] = false;
                remove.add(cursor);
                if (cursor >= lastCursor(n, flag)) {
                    while(!flag[cursor]) cursor--;
                } else {
                    while (!flag[cursor]) cursor++;
                }
                System.out.println(cursor);
            } else if (command.equals("Z")) {
                flag[remove.pop()] = true;
            } else if (command.contains("U")) {
                int count = Integer.parseInt(command.split(" ")[1]);
                int cnt = 0;
                while (cnt < count) {
                    cursor--;
                    if (flag[cursor]) cnt++;
                }
            } else {
                int count = Integer.parseInt(command.split(" ")[1]);
                int cnt = 0;
                while (cnt < count) {
                    cursor++;
                    if (flag[cursor]) cnt++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (flag[i]) answer.append("O");
            else answer.append("X");
        }
        return new String(answer);
    }

    private int lastCursor(int n, boolean[] flag) {
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (flag[i]) {
                res = i;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        System.out.println(sol.solution(8, 2, cmd));
    }
}
