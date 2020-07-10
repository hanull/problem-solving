package Programmers.Brute_Force;

import java.util.*;
  public class Solution42841 {
    public static int solution(int[][] baseball) {
      int answer = 0;
      for(int i=123; i<=987; i++){
          if (isPossible(i, baseball))
              answer++;
      }
      return answer;
  }
  public static boolean isPossible(int num, int[][] baseball){
      int numA=num/100;
      int numB=num/10%10;
      int numC=num%10;
      if (numA==numB || numA==numC || numB==numC || numB==0 || numC==0) return false;
      for(int len=0; len<baseball.length; len++){
          int strike=0;
          int ball=0;
          int compA=baseball[len][0]/100;
          int compB=baseball[len][0]/10%10;
          int compC=baseball[len][0]%10;
          if(numA==compA) strike++;
          if(numB==compB) strike++;
          if(numC==compC) strike++;
          if(numA!=compA && (numA==compB || numA==compC)) ball++;
          if(numB!=compB && (numB==compA || numB==compC)) ball++;
          if(numC!=compC && (numC==compB || numC==compA)) ball++;
          if(strike!=baseball[len][1] || ball!=baseball[len][2])
              return false;
      }
      return true;
  }

  public static void main(String[] args) {
    int[][] baseball = {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}};
    System.out.println(solution(baseball));
  }
}
