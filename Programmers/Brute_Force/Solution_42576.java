package Programmers.Brute_Force;

import java.util.*;

public class Solution_42576 {

  public static String solution(String[] participant, String[] completion) {
    Map<String, Integer> hashMap = new HashMap<>();
    String res = "";
    for (String runner : participant) {
      if (hashMap.containsKey(runner)) {
        hashMap.put(runner, hashMap.get(runner) + 1);
      }else {
        hashMap.put(runner, 1);
      }
    }
    for (String clearPeople : completion) {
      hashMap.put(clearPeople, hashMap.get(clearPeople) - 1);
    }
    for (String key : hashMap.keySet()) {
      if (hashMap.get(key) == 1) res = key;
    }
    return res;
  }
  public static void main(String[] args) {
    String[] participant = {"mislav", "stanko", "mislav", "ana"};
    String[] completion = {"stanko", "ana", "mislav"};
    System.out.println(solution(participant, completion));
  }
}
