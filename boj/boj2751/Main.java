package boj.boj2751;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    boolean[] num = new boolean[2000001];
    for(int i=0; i<n; i++) {
      num[sc.nextInt()+1000000] = true;
    }
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<=2000000; i++) {
      if(num[i])
        sb.append(i-1000000 + "\n");
    }
    System.out.println(sb.toString());
  }
}
