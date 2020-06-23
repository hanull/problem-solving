package Programmers.Stack_Queue;

public class Solution_42588 {
  public static int[] solution(int[] heights) {
    if (heights.length < 1){
      return null;
    }
    if (heights.length == 1) {
      int[] answer = {0};
      return answer;
    }
    int[] answer = new int[heights.length];
    for(int i=0; i<heights.length; i++) {
      for(int j=i; j>=0; j--) {
        if(heights[j] > heights[i]) {
          answer[i] = j+1;
          break;
        }
      }
    }
    return answer;
  }
  
  public static void main(String[] args) {
    int[] a = {6,9,5,7,4};
    int[] res = solution(a);
    for(int i=0; i<res.length; i++) {
      System.out.print(res[i]+ " ");
    }
  }
}
