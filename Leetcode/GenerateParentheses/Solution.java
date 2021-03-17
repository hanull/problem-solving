package Leetcode.GenerateParentheses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    static public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        select(0, 0, ret, "", n);
        return ret;
    }

    static void select(int openCnt, int closeCnt, List<String> ret, String str, int n) {
        if (openCnt == n && closeCnt == n) {
            ret.add(str);
            return;
        }
        if (openCnt < n) {
            select(openCnt + 1, closeCnt, ret, str + "(", n);
        }
        if (openCnt > closeCnt) {
            select(openCnt, closeCnt + 1, ret, str + ")", n);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(generateParenthesis(n));
    }
}
