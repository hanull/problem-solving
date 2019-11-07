import java.io.*;
import java.util.*;
public static int solution(String[][] clothes) {
    Map<String, Integer> hm = new HashMap<String, Integer>();
    int answer = 1;
    int len=clothes.length;
    for(int i=0; i<len; i++) {
      hm.put(clothes[i][1], hm.getOrDefault(clothes[i][1], 0)+1);
    }
    for(String key:hm.keySet()) { // key값의 옷을 선택하지 않을 경우가 있어서 +1을 해준다.
      answer*=hm.get(key)+1;
    }
    answer--;
    return answer;
  }
