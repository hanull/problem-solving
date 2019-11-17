public static int solution(int[] numbers, int target) {
    int answer = 0;
    answer = dfs(numbers,target,0,0);
    return answer;
  }
  public static int dfs(int[] numbers, int target, int index, int total) {
    if(index==numbers.length) {
      if(total==target) {
        return 1;
      }
      return 0;
    }
    return dfs(numbers,target,index+1,total+numbers[index])+dfs(numbers,target,index+1,total-numbers[index]);
  }
