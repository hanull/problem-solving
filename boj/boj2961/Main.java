package boj.boj2961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int gap = 1000000000;
    static Flavor[] foodList;
    static int[] isSelected;
    static class Flavor {
        int sour, bitter;

        public Flavor(int sour, int bitter) {
            this.sour = sour;
            this.bitter = bitter;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        foodList = new Flavor[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            foodList[i] = new Flavor(stoi(st.nextToken()), stoi(st.nextToken()));
        }

        for (int i = 1; i <= N; i++) {
            isSelected = new int[i];
            comb(0, 0, i);
        }
        System.out.println(gap);

    }

    static void comb(int cnt, int start, int goal) {
        if (cnt == goal) {
            gap = Math.min(cal(), gap);
            return;
        }

        for (int i = start; i < N; i++) {
            isSelected[cnt] = i;
            comb(cnt + 1, i + 1, goal);
        }
    }

    static int cal() {
        int sour = 1;
        int bitter = 0;
        for (int idx : isSelected) {
            sour *= foodList[idx].sour;
            bitter += foodList[idx].bitter;
        }
        return Math.abs(sour - bitter);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
