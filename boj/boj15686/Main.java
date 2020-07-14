package boj.boj15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair{
  int x;
  int y;
  public Pair(int x, int y) {
    this.x=x;
    this.y=y;
  }
}

public class Main {
  static int n,m;
  static ArrayList<Pair> listChicken = new ArrayList<>();
  static ArrayList<Pair> listHouse = new ArrayList<>();
  static boolean[] visited = new boolean[13];
  static int cityDis = Integer.MAX_VALUE;
  
  public static void dfs(int index, int cnt) {
    if (cnt == m) {
      int tmp = calDistance();
      if (cityDis>tmp) cityDis = tmp;
      return;
    }
    for(int i=index; i<listChicken.size(); i++) {
      visited[i]=true;
      dfs(i+1,cnt+1);
      visited[i]=false;
    }
  }
  
  public static int calDistance() {
    int[] total = new int[listHouse.size()];
    Arrays.fill(total, Integer.MAX_VALUE);
    int tmp = 0;
    for(int i=0; i<listHouse.size(); i++) {
      int x = listHouse.get(i).x;
      int y = listHouse.get(i).y;
      for(int j=0; j<listChicken.size(); j++) {
        if(visited[j]) {
          int cx = listChicken.get(j).x;
          int cy = listChicken.get(j).y;
          tmp = Math.abs(x - cx) + Math.abs(y - cy);
          if (total[i] > tmp) total[i] = tmp;
        }
      }
    }
    int res =0;
    for(int i=0; i<total.length; i++) {
      res+=total[i];
    }  
    return res;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    n = Integer.valueOf(tmp[0]);
    m = Integer.valueOf(tmp[1]);
    int[][] map = new int[n+1][n+1];
    for(int i=1; i<=n; i++) {
      tmp = br.readLine().split(" ");
      for(int j=1; j<=n; j++) {
        map[i][j] = Integer.valueOf(tmp[j-1]);
        if (map[i][j] == 2) listChicken.add(new Pair(i,j));
        if (map[i][j] == 1) listHouse.add(new Pair(i,j));
      }
    }
    dfs(0, 0);
    System.out.println(cityDis);
  }
}
