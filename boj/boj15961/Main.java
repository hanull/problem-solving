package boj.boj15961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] sushi = new int[n];
        int[] cnt = new int[d + 1];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        //  0 ~ k 까지의 먹은 초밥 개수
        Queue<Integer> q = new LinkedList<>();
        int total = 0;
        for (int i = 0; i < k; i++) {
            q.add(sushi[i]);
            cnt[sushi[i]] = cnt[sushi[i]] + 1;
            if (cnt[sushi[i]] == 1) total++;
        }
        int max = 0;
        int tmp = 0;
        for (int i = k; i < n + k; i++) {   // n + k 까지만 체크하면, 모든 경우의 수를 확인 가능.
            int point = i < n ? i : i % n;
            int pop = q.poll();
            cnt[pop]--;
            if (cnt[pop] == 0) total--;
            q.add((sushi[point]));
            cnt[sushi[point]]++;
            if (cnt[sushi[point]] == 1) total++;
            tmp = cnt[c] == 0 ? total + 1 : total;
            if (tmp > max) max = tmp;
        }
        System.out.println(max);
    }
}
