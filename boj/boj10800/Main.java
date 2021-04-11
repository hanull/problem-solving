package boj.boj10800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Ball> ballList = new ArrayList<>();
    static class Ball {
        int no, color, size;

        public Ball(int no, int color, int size) {
            this.no = no;
            this.color = color;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = stoi(br.readLine());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            ballList.add(new Ball(i, stoi(st.nextToken()), stoi(st.nextToken())));
        }
        Collections.sort(ballList, new Comparator<Ball>() {
            @Override
            public int compare(Ball o1, Ball o2) {
                if (o1.size == o2.size) {
                    return o1.color - o2.color;
                }
                return o1.size - o2.size;
            }
        });

        int[] answer = new int[200001];
        int[] colorSum = new int[2000001];
        int[] sizeSum = new int[2001];
        int totalSum = 0;
        for (int i = 1; i <= N; i++) {
            Ball ball = ballList.get(i - 1);
            int no = ball.no;
            int color = ball.color;
            int size = ball.size;

            totalSum += size;
            colorSum[color] += size;
            sizeSum[size] += size;

            answer[no] = totalSum - sizeSum[size] - colorSum[color] + size;
            if (i != 1 && isSame(ballList.get(i - 2), color, size)) {
                answer[no] = answer[ballList.get(i - 2).no];
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            result.append(answer[i]).append("\n");
        }
        System.out.print(result);
    }

    static boolean isSame(Ball preBall, int color, int size) {
        return preBall.color == color && preBall.size == size;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
