package Programmers;

import java.io.*;

public class hash_전화번호목록 {
  public static boolean solution(String[] phone_book) {
    for(int i=0; i<phone_book.length; i++) {
      for(int j=0; j<phone_book.length; j++) {
        if(i==j) continue;
        if(phone_book[j].startsWith(phone_book[i])) {
          return false;
        }
      }
    }
    return true;
}
  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] phone_book=br.readLine().split(" ");
    System.out.println(solution(phone_book));
  }
}
