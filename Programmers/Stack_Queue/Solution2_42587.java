package Programmers.Stack_Queue;

import java.util.*;

public class Solution2_42587 {
  public static int[] solution(int[] progresses, int[] speeds) {
    Queue<Integer> que = new LinkedList<>();
    List<Integer> arrayList = new ArrayList<>();
    for (int i=0; i<progresses.length; i++){
        int workingTime = cal(progresses[i], speeds[i]);
        if (!que.isEmpty() && que.peek() < workingTime){
            arrayList.add(que.size());
            que.clear();
        }
        que.add(workingTime);
    }
    arrayList.add(que.size());
    int[] answer = new int[arrayList.size()];
    for (int i=0; i<arrayList.size(); i++){
        answer[i] = arrayList.get(i);
    }
    return (answer);
  }
  public static int cal(int x, int y){
      int res = (100-x)/y;
      int remainder = (100-x)%y;
      return (remainder > 0 ? res+1 : res);
  }
  public static void main(String[] args) {
    int[] a = {93,30,55};
    int[] b= {1,30,5};
    int[] result = solution(a,b);
    System.out.println(result.length);
  }
}
