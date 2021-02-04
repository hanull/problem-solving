package SWEA.쇠막대기자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution2 {

    static int T;
    static char[] input;
    static Stack<Character> stack;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        T = stoi(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            input = br.readLine().toCharArray();
            stack = new Stack<>();

            int answer = 0;
            for (int i = 0; i < input.length; i++) {
                if (input[i] == '(') {
                    stack.push(input[i]);
                }
                else {
                    if (input[i - 1] == '(') {      // 레이저인 경우,
                        stack.pop();                // 막대기가 아니니까 제거한다.
                        answer += stack.size();     // 그리고 앞에 잘린 막대기 수 만큼 +
                    } else {                        // 막대기인 경우
                        answer++;                   // 막대기 하나가 끝났으니 +1
                        stack.pop();                // 시작할 때 막대기라고 표시했던 '(' 제거
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }


    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
