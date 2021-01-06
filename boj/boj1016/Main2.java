package boj.boj1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static long min, max;
    static boolean[] checked = new boolean[1000001];
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        min = stoi(st.nextToken());
        max = stoi(st.nextToken());

        for (long i = 2; i <= Math.sqrt(max); i++) {
            long powNum = i * i;
            long start = min % powNum == 0 ? min : min / powNum * powNum + powNum;
            for (long j = start; j <= max; j += powNum) {
                if (checked[(int) (j - min)]) continue;
                if (j % powNum != 0) continue;
                checked[(int) (j - min)] = true;
                cnt++;
            }
        }
        System.out.println(max - min + 1 - cnt);
    }

    private static long stoi(String input) {
        return Long.valueOf(input);
    }
}
