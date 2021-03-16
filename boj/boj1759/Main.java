package boj.boj1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int L, N;
    static char[] alpa, selected;
    static HashSet<Character> vowels = new HashSet<>();
    static {
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = stoi(st.nextToken());
        N = stoi(st.nextToken());
        alpa = new char[N];
        selected = new char[L];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            alpa[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alpa);

        findPassword(0, 0);
    }

    static void findPassword(int cnt, int start) {
        if (cnt == L) {
            if (isPossible()) System.out.println(new String(selected));
            return;
        }

        for (int i = start; i < N; i++) {
            selected[cnt] = alpa[i];
            findPassword(cnt + 1, i + 1);
        }
    }

    static boolean isPossible() {
        int cnt1 = 0;
        int cnt2 = 0;
        for (char ch : selected) {
            if (vowels.contains(ch)) {
                cnt1++;
            } else {
                cnt2++;
            }
        }
        return cnt1 >= 1 && cnt2 >= 2 ? true : false;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
