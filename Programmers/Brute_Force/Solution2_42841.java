package Programmers.Brute_Force;

import java.util.*;

public class Solution2_42841 {
  public static int[] solution(int[] answers) {
    int[][] person = {{1,2,3,4,5},
        {2,1,2,3,2,4,2,5},
        {3,3,1,1,2,2,4,4,5,5}};
    int[] score = new int[3];
    int cnt;
    for(int i=0; i<3; i++) {
      cnt=0;
      for (int number=0; number<answers.length; number++) {
        if(person[i][number%person[i].length] == answers[number]) {
          cnt++;
        }
      }
      score[i] = cnt;
    }
    int maxValue = Math.max(score[0], Math.max(score[1], score[2]));
    ArrayList<Integer> list = new ArrayList<>();
    for(int i=0; i<3; i++) {
      if(maxValue == score[i]) list.add(i+1);
    }
    return list.stream().mapToInt(i->i.intValue()).toArray();
  }

  public static void main(String[] args) {
    int[] answer = {1,3,2,4,2};
    int[] res = solution(answer);
    StringBuilder builder = new StringBuilder();
    builder.append("[");
    for(int i=0; i<res.length; i++) {
      builder.append(i + 1);
      if (i != res.length-1) {
        builder.append(", ");
      }
    }
    builder.append("]");
    System.out.println(builder.toString());
  }
}
