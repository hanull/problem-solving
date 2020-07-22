package boj.boj1747;

import java.util.*;

public class Main {
  public static boolean is_prime(int n)
  {
    if (n == 1) return false;
    for (int i =2; i <= Math.sqrt(n); i++)
      if (n % i == 0) return false;
    return true;
  }
  public static int palindrome(int n)
  {
    int num, tmp;
    if(!is_prime(n)) return palindrome(n + 1);
    tmp = n;
    int len = (int) (Math.log10(n) + 1);
    num = 0;
    for(int i = len - 1; i >= 0; i--)
    {
      num = (num * 10) + (tmp % 10);
      tmp /= 10;
    }
    if (num == n) return n;
    return palindrome(n+1);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    if(n < 1 || n > 1000000) return;
    System.out.println(palindrome(n));
  }
}
