package Programmers.Stack_Queue;

import java.util.LinkedList;
import java.util.Queue;



public class Solution_42586 {
  public static int[] solution(int[] progresses, int[] speeds) {
    int len = progresses.length;
    if (len < 1)
        return null;
    int[] workingDay = new int[len];
    for(int i=0; i<len; i++){
        workingDay[i] = cal(progresses[i], speeds[i]);
    }
    Queue<Integer> list = new LinkedList<>();
    int tmp = workingDay[0];
    int cnt = 1;
    for(int i=1; i<len; i++) {
      if (tmp >= workingDay[i]) {
        cnt++;
      }else {
        list.add(cnt);
        tmp = workingDay[i];
        cnt=1;
      }
      if (i==len-1)
        list.add(cnt);
    }
    int[] answer = new int[list.size()];
    for(int i=0; i<answer.length; i++) {
      answer[i] = list.peek();
      list.remove();
    }
    return answer;
  }
  public static int cal(int x, int y){
      int res = (100 - x) / y;
      return (((100 - x) % y) > 0 ? res + 1 : res);
  }
  public static void main(String[] args) {
    int[] a = {93,30,55};
    int[] b= {1,30,5};
    int[] result = solution(a,b);
    System.out.println(result.length);
  }
}
