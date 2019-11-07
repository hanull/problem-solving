import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int len = commands.length;
        int[] answer = new int[len];
        for(int i=0; i<len; i++) {
          int startIndex=commands[i][0]-1;
          int endIndex=commands[i][1]-1;
          int resultIndex=commands[i][2]-1;
          int[] tmp=new int[endIndex-startIndex+1];
          for(int a=0; a<tmp.length; a++) {
            tmp[a]=array[startIndex++];
          }
          Arrays.sort(tmp);
          answer[i]=tmp[resultIndex];
        }
        return answer;
    }
}
