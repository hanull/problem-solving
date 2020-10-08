package boj.boj1389;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
  static final int INF = 101;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = stoi(st.nextToken());
    int m = stoi(st.nextToken());
    int[][] map = new int[n+1][n+1];
    int[] total = new int[n+1];
    int min = INF;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        if (i != j) map[i][j] = INF;
      }
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = stoi(st.nextToken());
      int b = stoi(st.nextToken());
      map[a][b] = 1;
      map[b][a] = 1;
    }
    for (int t = 1; t <= n; t++) {
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
          if (i == j) continue;
          if (map[i][j] > map[i][t] + map[t][j]) {
            map[i][j] = map[i][t] + map[t][j];
          }
        }
      }
    }
    for (int i = 1; i <= n; i++) {
      int tmp = 0;
      for (int j = 1; j <= n; j++) {
        if (i == j) continue;
        tmp += map[i][j];
      }
      if (min > tmp) min = tmp;
      total[i] = tmp;
    }
    for (int i = 1; i <= n; i++) {
      if (min == total[i]) {
        System.out.println(i);
        break;
      }
    }

  }

  private static int stoi(String input) {
    return Integer.valueOf(input);
  }

}
