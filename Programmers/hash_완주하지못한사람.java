package Programmers;
import java.io.*;
import java.util.*;

public class hash_완주하지못한사람 {
  public static String solution(String[] participant, String[] completion) {
    Map<String, Integer> map = new HashMap<String, Integer>();
    String result="";
    for(String str:participant) { // 참가자 등록
      if(map.containsKey(str)) {
        map.replace(str, map.get(str)+1);
      }
      else {
        map.put(str, 1);
      }
    }
    for(String str:completion) { // 완주자 체크
      if(map.containsKey(str)) {
        map.replace(str, map.get(str)-1);
      }
    }
    for(String str:participant) { // 완주하지 못한 사람 출력.
      if(map.get(str)!=0) {
        result=str;
      }

    }
    return result;
  }
  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] participant=br.readLine().split(" ");
    String[] completion=br.readLine().split(" ");
    System.out.println(solution(participant, completion));
  }
}
