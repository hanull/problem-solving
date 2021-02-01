package boj.boj10096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ** NOT UNIQUE **
// S = AABA
// T = AABAAABA
// U = AAABAAABA
// =>> AAAB, AAAB, A

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        String input = br.readLine();
        int mid = N / 2;

        if (N % 2 == 0) {
            System.out.println("NOT POSSIBLE");
            System.exit(0);
        }

        String left = input.substring(0, mid);
        String right = input.substring(mid, N);
        boolean flag1 = isPossible(left, right);

        String left2 = input.substring(mid + 1, N);
        String right2 = input.substring(0, mid + 1);
        boolean flag2 = isPossible(left2, right2);

        if (flag1 && flag2) {
            if (!left.equals(left2)) System.out.println("NOT UNIQUE");
            else System.out.println(left);
        } else if (flag1) {
            System.out.println(left);
        } else if (flag2) {
            System.out.println(left2);
        } else {
            System.out.println("NOT POSSIBLE");
        }

    }

    static boolean isPossible(String left, String right) {
        int diff = 0;
        int j = 0;
        for (int i = 0; i < left.length(); i++) {
            if (diff > 1) return false;
            if (left.charAt(i) != right.charAt(j)) {
                i--;
                j++;
                diff++;
            } else {
                j++;
            }
        }
        return true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
