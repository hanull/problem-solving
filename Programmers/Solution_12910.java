package Programmers;

import java.util.Arrays;

public class Solution_12910 {
    public static void main(String[] args) {
        Solution12910 sol = new Solution12910();
        int[] arr = {5, 9, 7, 10};
        int divisor = 11;
        System.out.println(sol.solution(arr, divisor));
    }
}

class Solution12910 {
    public int[] solution(int[] arr, int divisor) {
        int[] res = Arrays.stream(arr).sorted().filter(i -> i % divisor == 0).toArray();
        return res.length != 0 ? res : new int[]{-1};
    }

}
