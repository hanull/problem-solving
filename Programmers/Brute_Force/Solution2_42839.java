package Programmers.Brute_Force;

import java.util.HashSet;

public class Solution2_42839 {
  
  public static int solution(String numbers) {
    HashSet<Integer> set = new HashSet<>();
    permutation("", numbers, set);
    int cnt = 0;
    while (set.iterator().hasNext()) {
        int data = set.iterator().next();
        if (isPrime(data)){
            cnt++;
        }
        set.remove(data);
    }
    return cnt;
  }
  
  public static void permutation(String prefix, String str, HashSet<Integer> set){
      int len = str.length();
      if (!prefix.equals("")){
          set.add(Integer.valueOf(prefix));
      }
      for(int i=0; i<len; i++){
          permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, len), set);
      }
  }
  
  public static boolean isPrime(int num){
      if (num==0 || num ==1) return false;
      if (num==2) return true;
      if (num%2 == 0) return false;
      for(int i=3; i<=Math.sqrt(num); i+=2){
          if (num%i == 0) return false;
      }
      return true;
  }
  
  public static void main(String[] args) {
    System.out.println(solution("12345"));
  }
}
