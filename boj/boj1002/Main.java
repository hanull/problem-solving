package boj.boj1002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int T, x1, x2, y1, y2, r1, r2;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = stoi(br.readLine());

        while (T-- > 0) {
            input();

            int distance_pow = (int)(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

            // 중점, 반지름도 같을 경우
            if (x1 == x2 && y1 == y2 && r1 == r2) System.out.println(-1);

            // 두 원의 반지름 합보다 중점간 거리가 더 길 때, 접점이 없을 때
            else if(distance_pow > Math.pow(r1 + r2, 2)) System.out.println(0);

            // 원 안에 원이 있고 내접하지 않을 때
            else if(distance_pow < Math.pow(r2 - r1, 2)) System.out.println(0);

            // 내접할 때
            else if(distance_pow == Math.pow(r2 - r1, 2)) System.out.println(1);

            // 외접할 때
            else if(distance_pow == Math.pow(r1 + r2, 2)) System.out.println(1);

            else System.out.println(2);
        }
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        x1 = stoi(st.nextToken());
        y1 = stoi(st.nextToken());
        r1 = stoi(st.nextToken());
        x2 = stoi(st.nextToken());
        y2 = stoi(st.nextToken());
        r2 = stoi(st.nextToken());
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
