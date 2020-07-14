package boj.boj1389;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {
  static final int INF = 101;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    int n = Integer.valueOf(tmp[0]);
    int m = Integer.valueOf(tmp[1]);
    int[][] map = new int[n+1][n+1];
    for(int i=1; i<=n; i++) {
      for(int j=1; j<=n; j++) {
        if(i!=j)
          map[i][j] = INF;
      }
    }
    for(int i=1; i<=m; i++) {
      tmp = br.readLine().split(" ");
      int a = Integer.valueOf(tmp[0]);
      int b = Integer.valueOf(tmp[1]);
      map[a][b] = 1;
      map[b][a] = 1;
    }
    for(int k=1; k<=n; k++) {
      for(int from=1; from<=n; from++) {
        for(int to=1; to<=n; to++) {
          if (map[from][to]>map[from][k] + map[k][to])
            map[from][to] = map[from][k] + map[k][to];
        }
      }
    }
    int res = 0;
    int min = INF;
    for(int i=1; i<=n; i++) {
      int total = 0;
      for(int j=1; j<=n; j++) {
        total+=map[i][j];
      }
      if (min > total) {
        min = total;
        res = i;
      }
    }
    System.out.println(res);
  }
}
