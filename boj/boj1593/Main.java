package boj.boj1593;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());
        int len = stoi(st.nextToken());
        char[] target = br.readLine().toCharArray();
        char[] fullWord = br.readLine().toCharArray();
        if (n == 1 && len == 1 && target[0] == fullWord[0]) {
            System.out.println(1);
            System.exit(0);
        }

        int[] alphabet = new int[52];
        for (char ch : target) {
            updateCount(alphabet, ch, 1);
        }
        int answer = 0;
        int left = 0;
        int[] temp = new int[52];
        for (int i = 0; i < len; i++) {
            updateCount(temp, fullWord[i], 1);
            if (i - left + 1 == n) {
                if (isPossible(alphabet, temp)) {
                    answer++;
                }
                updateCount(temp, fullWord[left], -1);
                left++;
            }
        }
        System.out.println(answer);
    }

    private static void updateCount(int[] temp, char ch, int num) {
        if (ch >= 'a' && ch <= 'z') {
            temp[ch - 'a'] += num;
        } else {
            temp[ch - 'A' + 26] += num;
        }
    }

    private static boolean isPossible(int[] alphabet, int[] temp) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] != temp[i]) return false;
        }
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
