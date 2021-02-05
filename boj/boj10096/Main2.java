package boj.boj10096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

    static int N;
    static String inputStr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        inputStr = br.readLine();
        System.out.println(findStringS());
    }

    static String findStringS() {
        if (N % 2 == 0) return "NOT POSSIBLE";
        int mid = N / 2;
        String left1 = inputStr.substring(0, mid + 1);
        String right1 = inputStr.substring(mid + 1, N);
        boolean result1 = isContainString(left1, right1);

        String left2 = inputStr.substring(0, mid);
        String right2 = inputStr.substring(mid, N);
        boolean result2 = isContainString(right2, left2);

        if (result1 && result2) {
            if (right1.equals(left2)) return right1;
            else return "NOT UNIQUE";
        } else if (result1) {
            return right1;
        } else if (result2) {
            return left2;
        } else {
            return "NOT POSSIBLE";
        }
    }

    static boolean isContainString(String baseString, String compareString) {
        int j = 0;
        int wrong = 0;
        for (int i = 0; i < compareString.length(); i++) {
            if (baseString.charAt(j) != compareString.charAt(i)) {
                i--;
                j++;
                wrong++;
                if (wrong > 1) break;
            } else {
                j++;
            }
        }
        return wrong > 1 ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
