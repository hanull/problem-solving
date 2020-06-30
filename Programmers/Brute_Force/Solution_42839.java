package Programmers.Brute_Force;

import java.util.Arrays;
import java.util.Comparator;

public class Solution_42839 {
  public static int solution(String numbers) {
    int[] arrNums = Arrays.stream(numbers.split(""))
        .sorted(Comparator.reverseOrder())
        .mapToInt(x -> Integer.valueOf(x))
        .toArray();
    int max = 0;
    for(int i=0; i<arrNums.length; i++) {
      max += arrNums[i]* Math.pow(10,arrNums.length - 1 - i);
    }
    int [] prime = new int[max+1];
    Arrays.setAll(prime, i -> i);
    prime[1]=0;
    for(int i=2; i<=max; i++) {
      if (prime[i] == 0) continue;
      for(int j=i*2; j<=max; j+=i) {
        prime[j] = 0;
      }
    }
    int cnt = 0;
    for(int i=2; i<=max; i++) {
      if (prime[i] !=0 && isPossible(i, arrNums))
        cnt++;
    }
    return cnt;
  }
  
  public static boolean isPossible(int x, int[] arr) {
    boolean[] check = new boolean[arr.length];
    String str = String.valueOf(x);
    int cnt = 0;
    // 소수이면서, 주어진 숫자로 만들 수 있는 값만 리턴
    for(int i=0; i<str.length(); i++) {
      int tmp = str.charAt(i) - '0';
      for(int j=0; j<arr.length; j++) {
        if (!check[j] && tmp == arr[j]) {
          check[j] = true;
          cnt++;
          break;
        }
      }
    }
    return cnt != str.length() ? false : true;
  }
  
  public static void main(String[] args) {
    System.out.println(solution("17"));
  }
}
