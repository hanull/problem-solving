package Programmers;
import java.io.*;
import java.util.*;

public class
hash_위장 {
  
  public static int solution(String[][] clothes) {
    Map<String, Integer> hm = new HashMap<String, Integer>();
    int answer = 1;
    int len=clothes.length;
    for(int i=0; i<len; i++) {
      hm.put(clothes[i][1], hm.getOrDefault(clothes[i][1], 0)+1);
    }
    for(String key:hm.keySet()) {
      answer*=hm.get(key)+1;
    }
    answer--;
    return answer;
  }

  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] line=br.readLine().split(",");
    String[][] clothes=new String[line.length][2];
    for(int i=0; i<line.length; i++) {
      String[] str=line[i].split(" ");
      clothes[i][0]=str[0];
      clothes[i][1]=str[1];
    }
    System.out.println(solution(clothes));

  }
}
