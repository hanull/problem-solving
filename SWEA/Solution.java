package SWEA;
import java.util.*;

public class Solution {
  static int C, k;
  static String str;
  static int[] num;
  static int result, max;
  public static void dfs(int index, int cnt) {
    if(cnt==k) {
      int total=0;
      for(int i=0; i<num.length; i++) {
        total+=num[i]*(Math.pow(10, num.length-i-1));
      }
      if(total>result) result=total;
      return;
    }
    for(int i=index; i<num.length; i++) {
      for(int j=i+1; j<num.length; j++) {
        if(num[i]<=num[j]) {
          swap(i,j);
          dfs(i,cnt+1);
          swap(i,j);
        }
      }
    }
  }

  public static void swap(int a, int b) {
    int tmp = num[a];
    num[a]=num[b];
    num[b]=tmp;
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    C=sc.nextInt();
    for(int c=1; c<=C; c++) {
      str=sc.next();
      k=sc.nextInt();
      num=new int[str.length()];
      result=0;
      for(int i=0; i<str.length(); i++) {
        num[i]=str.charAt(i)-'0';
      }
      dfs(0,0);
      if(result==0) {
        result=Integer.parseInt(str);
      }
      System.out.println("#"+c+" "+ result);
    }
  }
}