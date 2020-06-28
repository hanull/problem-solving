package Programmers.Brute_Force;

import java.util.Stack;

class Pair{
  int no;
  int cnt;
  public Pair(int no, int cnt) {
    this.no = no;
    this.cnt = cnt;
  }
}
public class Solution_42840 {
  public static int[] solution(int[] answers) {
    int[][] person = {{1,2,3,4,5},
        {2,1,2,3,2,4,2,5},
        {3,3,1,1,2,2,4,4,5,5}};
    Stack<Pair> stack = new Stack<>();
    int cnt;
    for (int i=0; i<3; i++) {
      cnt=0;
      for (int number=0; number<answers.length; number++) {
        if (person[i][number%person[i].length] == answers[number]) {
          cnt++;
        }
      }
      if (stack.isEmpty()) {
        stack.add(new Pair(i, cnt));
      }else {
        if (stack.peek().cnt == cnt) {
          stack.add(new Pair(i, cnt));
        }else if (stack.peek().cnt < cnt) {
          stack.clear();
          stack.add(new Pair(i, cnt));
        }
      }
    }
    int[] res = new int[stack.size()];
    for(int i=res.length-1; i>=0; i--) {
      res[i] = stack.pop().no + 1;
    }
    return res;
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
