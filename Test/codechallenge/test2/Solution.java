package Test.codechallenge.test2;

import java.util.Stack;

public class Solution {

    public static int solution(String s) {
        int answer = 0;
        String input = s;
        int len = input.length();
        for (int i = 0; i < s.length(); i++) {
            String bracket = input.substring(i, len) + input.substring(0, i);
            if (isPossible(bracket)) answer++;
        }
        return answer;
    }

    static boolean isPossible(String bracket) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < bracket.length(); i++) {
            char ch = bracket.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.add(ch);
            } else {
                if (stack.isEmpty()) return false;
                if (ch == ')') {
                    if (stack.peek() != '(') return false;
                    else stack.pop();
                } else if (ch == '}') {
                    if (stack.peek() != '{') return false;
                    else stack.pop();
                } else {
                    if (stack.peek() != '[') return false;
                    else stack.pop();
                }
            }
        }
        return stack.isEmpty() ? true : false;
    }

    public static void main(String[] args) {
        String s = "}]()[{";
        System.out.println(Solution.solution(s));
    }
}
