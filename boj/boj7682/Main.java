package boj.boj7682;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int emptyCount, xCount, oCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = br.readLine();
            if (input.equals("end")) break;
            emptyCount = xCount = oCount = 0;
            countInput(input);
            System.out.println(isPossible(input) ? "valid" : "invalid");
        }
    }

    private static boolean isPossible(String input) {
        if (emptyCount == 0) {  // 빈 칸이 없는 경우, X의 수는 O의 수 보다 1많다. 또한, 연속되는 O는 없다.
            if (xCount != oCount + 1) return false;
            if (connectionCount('O', input) > 0) return false;
        } else if (emptyCount % 2 == 0) {   // X가 이기는 경우. X의 수가 O의 수보다 1많다. 연속된 X가 단 1개만 존재. 연속된 O는 없다.
            if (xCount != oCount + 1) return false;
            if (connectionCount('X', input) != 1) return false;
            if (connectionCount('O', input) != 0) return false;
        } else {    // O가 이기는 경우. X의 개수와 O의 개수는 같다. X는 연속되지 않는다. O은 단 하나만 연속됨.
            if (xCount != oCount) return false;
            if (connectionCount('X', input) > 0) return false;
            if (connectionCount('O', input) != 1) return false;
        }
        return true;
    }

    private static int connectionCount(char ch, String input) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            // 가로 체크
            boolean flag = true;
            for (int j = 0; j < 3; j++) {
                if (input.charAt(i * 3 + j) != ch) {
                    flag = false;
                    break;
                }
            }
            if (flag) count++;

            // 세로 체크
            flag = true;
            for (int j = i; j < 9; j += 3) {
                if (input.charAt(j) != ch) {
                    flag = false;
                    break;
                }
            }
            if (flag) count++;
        }
        // 대각선 체크
        if (input.charAt(0) == ch && input.charAt(4) == ch && input.charAt(8) == ch) count++;
        if (input.charAt(2) == ch && input.charAt(4) == ch && input.charAt(6) == ch) count++;
        return count;
    }

    private static void countInput(String input) {
        for (char ch : input.toCharArray()) {
            if (ch == 'X') {
                xCount++;
            } else if (ch == 'O') {
                oCount++;
            } else {
                emptyCount++;
            }
        }
    }
}
