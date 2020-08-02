package Programmers;
import java.io.*;
import java.util.*;

public class 기능개발 {
  public static int[] solution(int[] progresses, int[] speeds) {
    int[] arr=new int[progresses.length];
    ArrayList<Integer> list = new ArrayList<>();
    //Queue<Integer> list = new LinkedList<Integer>();
    for(int i=0; i<progresses.length; i++) {
      int cnt=(100-progresses[i])/speeds[i];
      int tmp=(100-progresses[i])%speeds[i];
      if(tmp>0) cnt++;
      arr[i]=cnt;
      list.add(cnt);
    }
    Queue<Integer> q = new LinkedList<Integer>();
    boolean[] visited = new boolean[list.size()];
    int first=arr[0];
    int cnt=0;
    int index=0;
    while(true) {
      if(index==list.size()) {
        q.add(cnt);
        break;
      }
      if(visited[index]) {
        index++;
        continue;
      }
      if(first>=list.get(index)) {
        cnt++;
        visited[index]=true;
      }
      else {
        first=list.get(index);
        q.add(cnt);
        cnt=0;
      }
    }
    int[] answer = new int[q.size()];
    int len = q.size();
    for(int i=0; i<len; i++) {
      int num=q.poll();
      answer[i]=num;
      System.out.println(answer[i]);
    }
    return answer;
  }

  public static void main(String[] args) throws Exception{
    int[] progresses = {93,30,55};
    int[] speeds = {1,30,5};
    System.out.println(solution(progresses, speeds));
  }
}
