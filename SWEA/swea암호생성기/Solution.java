package SWEA.swea암호생성기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static Queue<Integer> q;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= 10; tc++) {
            q = new LinkedList<>();
            N = stoi(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                q.add(stoi(st.nextToken()));
            }

            boolean flag = false;
            while (true) {
                for (int i = 1; i <= 5; i++) {
                    int num = q.poll() - i;
                    if (num <= 0) {
                        q.add(0);
                        flag = true;
                        break;
                    } else {
                        q.add(num);
                    }
                }
                if (flag) break;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#" + tc + " ");
            while (!q.isEmpty()) {
                sb.append(q.poll() + " ");
            }
            System.out.println(sb);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
