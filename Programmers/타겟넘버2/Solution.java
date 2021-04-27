package Programmers.타겟넘버2;

class Solution {

    static int answer;

    public int solution(int[] numbers, int target) {
        dfs(numbers, numbers.length, 0, 0, target);
        return answer;
    }

    static void dfs(int[] numbers, int len, int index, int total, int target) {
        if (index == len) {
            if (total == target) answer++;
            return;
        }
        dfs(numbers, len, index + 1, total + numbers[index], target);
        dfs(numbers, len, index + 1, total - numbers[index], target);
    }
}
