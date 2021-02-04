package SWEA.쇠막대기자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int T;
    static char[] input;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        T = stoi(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            input = br.readLine().toCharArray();
            int answer = 0;
            int cnt = 0;
            for (int i = 0; i < input.length - 1; i++) {
                if (input[i] == '(') {
                    if (input[i + 1] == ')') {      // 레이져인 경우,
                        answer += cnt;
                        i++;
                    } else {    // 막대기인 경우,
                        cnt++;
                    }
                }
                else {
                    answer++;
                    cnt--;
                }
            }
            answer += cnt;
            System.out.println("#" + tc + " " + answer);
        }


    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
