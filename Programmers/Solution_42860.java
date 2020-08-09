package Programmers;

public class Solution_42860 {
    static int answer = 0;
    public static int solution(String name) {
        int len = name.length();
        int count = len;
        boolean[] check = new boolean[len];
        for (int i=0; i<len; i++) {
            if (name.charAt(i)=='A') {
                count--;
                check[i] = true;
            }
        }
        int next = 0;
        int cur = 0;
        while (count > 0) {
            next = getIndex(cur, check, name);
            changeAlpa(name.charAt(next));
            cur = next;
            check[cur] = true;
            count--;
        }
        return answer;
    }
    private static void changeAlpa(char tmp) {
        int up = tmp - 'A';
        int down = 'Z' - tmp + 1;
        if (up > down) answer += down;
        else answer += up;
    }

    private static int getIndex(int cur, boolean[] check, String name) {
        int len = name.length();

        // left
        int i = cur;
        int left = 0;
        while (check[i] || name.charAt(i)=='A') {
            if (i == 0) i = len - 1;
            else i--;
            left++;
        }
        // right
        int j = cur;
        int right = 0;
        while (check[j] || name.charAt(i)=='A') {
            if (j == len-1) j = 0;
            else j++;
            right++;
        }
        if (right > left) answer += left;
        else answer += right;
        return right > left ? i : j;
    }
}
