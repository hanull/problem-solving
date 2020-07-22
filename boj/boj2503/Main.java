package boj.boj2503;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair{
  int num;
  int strike;
  int ball;
  public Pair(int num, int strike, int ball) {
    this.num=num;
    this.strike=strike;
    this.ball=ball;
  }
}
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    Pair[] list = new Pair[n];
    for(int i=0; i<n; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      int c = sc.nextInt();
      list[i] = new Pair(a,b,c);
    }
    Queue<Integer> q = new LinkedList<Integer>();
    boolean flag;
    for(int i=123; i<=987; i++) {
      int base1 = i/100;
      int base2 = i/10%10;
      int base3 = i%10;
      if (base1==base2 || base1==base3 || base2==base3 || base2==0 || base3==0) continue;
      flag=true;
      for(int j=0; j<n; j++) {
        int tmp1 = list[j].num/100;
        int tmp2 = list[j].num/10%10;
        int tmp3 = list[j].num%10;
        int strike=0;
        int ball=0;
        if(base1==tmp1) strike++;
        if(base2==tmp2) strike++;
        if(base3==tmp3) strike++;
        if(base1!=tmp1 && (base1==tmp2||base1==tmp3)) ball++;
        if(base2!=tmp2 && (base2==tmp1||base2==tmp3)) ball++;
        if(base3!=tmp3 && (base3==tmp1||base3==tmp2)) ball++;
        if (strike!=list[j].strike || ball!=list[j].ball) {
          flag=false;
          break;
        }
      }
      if(flag)
        q.add(i);
    }
    System.out.println(q.size());
  }
}
