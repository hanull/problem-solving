import java.util.*;
class Solution {
    public static String solution(int[] numbers){
      String[] tmp=toStringArray(numbers);
      Arrays.sort(tmp,(o1,o2)->(o2+o1).compareTo(o1+o2));
      return printMaxnum(tmp);
    }
    public static String[] toStringArray(int[] numbers) {
      String[] arr=new String[numbers.length];
      for(int i=0; i<arr.length; i++) {
        arr[i]=String.valueOf(numbers[i]);
      }
      return arr;
    }
    public static String printMaxnum(String[] tmp) {
      String result="";
      for(int i=0; i<tmp.length; i++) {
        if(tmp[0].equals("0")) return "0";
        result+=tmp[i];
      }
      return result;
    }
}
