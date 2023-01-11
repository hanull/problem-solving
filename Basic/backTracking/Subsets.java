package Basic.backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(answer, new ArrayList<>(), nums, 0);
        return answer;
    }

    private void backtrack(final List<List<Integer>> answer, final List<Integer> tempList, final int[] nums,
                           final int start) {
        answer.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(answer, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subsets sol = new Subsets();
        final List<List<Integer>> answer = sol.subsets(new int[]{1, 2, 3});
        for (List<Integer> nums : answer) {
            System.out.println(Arrays.toString(new List[]{nums}));
        }
    }
}
