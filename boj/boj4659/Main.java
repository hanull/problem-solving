package boj.boj4659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static char[] vowel = {'a','e','i','o','u'};
  
  public static boolean isVowel(char ch) {
    for(int i=0; i<vowel.length; i++) {
      if (ch==vowel[i])
        return true;
    }
    return false;
  }
  public static boolean isPossible(String line) {
    // 모음을 하나라도 포함하고 있는가??
    // 모음이 없으면 false;
    int len = line.length();
    for(int i=0; i<len; i++) {
      if (isVowel(line.charAt(i)))
        break;
      else if (i==len-1)
        return false;
    }
    
    // 모음이나 자음이 3개 연속으로 나올 경우
    // 모음=1, 자음=0
    int[] check = new int[len];
    for(int i=0; i<len; i++) {
      char tmp = line.charAt(i);
      if (isVowel(tmp))
        check[i] = 1;
    }
    boolean flag=true;
    for(int i=0; i<len-2; i++) {
      int tmp = check[i];
      flag=true;
      for(int j=i+1; j<i+3; j++) {
        if(tmp!=check[j]) {
          flag=false;
          break;
        }
      }
      if(flag)
        return false;
    }
    
    // "ee", "oo" 가 아닌 연속적인 글자가 나올 경우
    for(int i=0; i<len-1; i++) {
      char tmp1 = line.charAt(i);
      char tmp2 = line.charAt(i+1);
      if (tmp1==tmp2 && !(tmp1=='e' || tmp1=='o'))
        return false;
    }
    return true;
  }
  public static String appendString(String line) {
    StringBuilder sb = new StringBuilder();
    sb.append("<");
    sb.append(line);
    sb.append("> ");
    if (isPossible(line)) {
      sb.append("is acceptable.\n");
    }else {
      sb.append("is not acceptable.\n");
    }
    return sb.toString();
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line="";
    StringBuilder sb = new StringBuilder();
    while (!(line=br.readLine()).equals("end")) {
      String tmp = appendString(line);
      sb.append(tmp);
    }
    System.out.println(sb.toString());
  }
}
