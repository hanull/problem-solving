package Programmers;
public class Å¸°Ù³Ñ¹ö {
  static int answer=0;
  public static int solution(int[] numbers, int target) {
    dfs(numbers,target,0,0);
    return answer;
  }
  public static void dfs(int[] numbers, int target, int index, int total) {
    if(index==numbers.length) {
      if(total==target) {
        answer++;
        return;
      }
      return;
    }
    dfs(numbers,target,index+1,total+numbers[index]);
    dfs(numbers,target,index+1,total-numbers[index]);
  }
  public static void main(String[] args) {
    int[] numbers= {1, 1, 1, 1, 1};
    int target=3;
    System.out.println(solution(numbers, target));
  }
}
