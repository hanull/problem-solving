package Programmers.Brute_Force;

public class Solution_42842 {
  public static int[] solution(int brown, int yellow) {
    int[] res = new int[2];
    int totalBlock = brown + yellow;
    for(int i=1; i<=Math.sqrt(totalBlock); i++) {
      if (totalBlock%i==0) {
        int row = totalBlock/i;
        if (isAnswer(row, i, brown, yellow)) {
          res[0] = row;
          res[1] = i;
          break;
        }
      }
    }
    return res;
  }
  
  public static boolean isAnswer(int row, int col, int brown, int yellow) {
    int totalBlock = brown + yellow;
    int checkBrown = (row*2) + (col*2) - 4;
    int checkYellow = totalBlock - brown;
    if (!(checkBrown==brown)&&(checkYellow==yellow)) {
      return false;
    }
    return true;
  }
  
  public static void main(String[] args) {
    int[] res = solution(8, 1);
    for(int i=0; i<2; i++) {
      System.out.println(res[i]);
    }
  }
}
