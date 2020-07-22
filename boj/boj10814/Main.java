package boj.boj10814;

import java.util.Arrays;
import java.util.Scanner;

class Pair implements Comparable<Pair>{
  int age;
  String name;
  int order;
  public Pair(int age, String name,int order) {
    this.age=age;
    this.name=name;
    this.order=order;
  }
  @Override
  public int compareTo(Pair tmp) {
    if (age==tmp.age) {
      return order-tmp.order;
    }
    return age-tmp.age;
  }
}
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    Pair[] list = new Pair[n];
    for(int i=0; i<n; i++) {
      int age = sc.nextInt();
      String name = sc.next();
      list[i] = new Pair(age,name,i);
    }
    Arrays.sort(list);
    for(int i=0; i<n; i++)
      System.out.println(list[i].age + " " + list[i].name);
  }
}
