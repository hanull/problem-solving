package Programmers.합승택시;
import java.util.*;

class Solution {

    static final int MAX = 100000000;
    static int[][] minFares;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = MAX;
        minFares = new int[n+1][n+1];
        for (int i=1; i<=n; i++) {
            Arrays.fill(minFares[i], MAX);
            minFares[i][i] = 0;
        }

        for (int i=0; i<fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int fare = fares[i][2];
            minFares[from][to] = minFares[to][from] = fare;
        }

        for (int mid=1; mid<=n; mid++) {
            for (int from=1; from<=n; from++) {
                for (int to=1; to<=n; to++) {
                    if (minFares[from][to] > minFares[from][mid] + minFares[mid][to]) {
                        minFares[from][to] = minFares[from][mid] + minFares[mid][to];
                    }
                }
            }
        }

        for (int to=1; to<=n; to++) {
            int total = minFares[s][to] + minFares[to][a] + minFares[to][b];
            answer = Math.min(answer, total);
        }

        return answer;
    }
}