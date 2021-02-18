package jungol.냉장고;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Chemical[] chemicals;
    static class Chemical implements Comparable<Chemical>{
        int lowTemperature, highTemperature;

        public Chemical(int lowTemperature, int highTemperature) {
            this.lowTemperature = lowTemperature;
            this.highTemperature = highTemperature;
        }

        @Override
        public int compareTo(Chemical o) {
            return Integer.compare(this.highTemperature, o.highTemperature);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        chemicals = new Chemical[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            chemicals[i] = new Chemical(stoi(st.nextToken()), stoi(st.nextToken()));
        }
        Arrays.sort(chemicals);
        int cnt = 1;
        int idx = 0;
        for (int i = 1; i < N; i++) {
            if (chemicals[i].lowTemperature > chemicals[idx].highTemperature) {
                cnt++;
                idx = i;
            }
        }

        System.out.println(cnt);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
