package Programmers.타겟넘버re;

public class Solution {

    static int answer, len;

    public int solution(int[] numbers, int target) {
        len = numbers.length;
        dfs(0, 0, numbers, target);
        return answer;
    }

    private void dfs(int index, int total, int[] numbers, int target) {
        if (index == len) {
            if (total == target) answer++;
            return;
        }
        dfs(index + 1, total + numbers[index], numbers, target);
        dfs(index + 1, total + numbers[index] * -1, numbers, target);
    }
}
