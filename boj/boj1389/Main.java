package boj.boj1389;

import java.util.*;

class Pair{
  int num;
  int cnt;
  public Pair(int num, int cnt) {
    this.num = num;
    this.cnt = cnt;
  }
}

public class Main {
  static int n,m;
  static boolean[] visited;
  static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
  
  public static int bfs(int start, int end) {
    Queue<Pair> q = new LinkedList<>();
    q.add(new Pair(start,0));
    visited[start] = true;
    while (!q.isEmpty()) {
      Pair tmp = q.poll();
      int num = tmp.num;
      int cnt = tmp.cnt;
      if (tmp.num == end) {
        return tmp.cnt;
      }
      for(int i=0; i<list.get(num).size(); i++) {
        if (!visited[list.get(num).get(i)]) {
          q.add(new Pair(list.get(num).get(i), cnt+1));
          visited[list.get(num).get(i)]=true;
        }
      }
    }
    return 0;
  }
  public static int getPeople(int min, int[] total) {
    for(int i=1; i<total.length; i++) {
      if(total[i]==min)
        return i;
    }
    return 0;
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    for(int i=0; i<=n; i++)
      list.add(new ArrayList<>());
    for(int i=1; i<=m; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      list.get(a).add(b);
      list.get(b).add(a);
    }
    int[] total = new int[n+1];
    total[0]=Integer.MAX_VALUE;
    for(int i=1; i<=n; i++) {
      for(int j=1; j<=n; j++) {
        visited = new boolean[n+1];
        if(i!=j)
          total[i]+=bfs(i,j);
      }
    }
    int min = Arrays.stream(total).min().getAsInt();
    System.out.println(getPeople(min, total));
  }
}
