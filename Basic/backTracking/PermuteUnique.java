package Basic.backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * contains duplicates
 */
public class PermuteUnique {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(answer, new ArrayList<>(), nums, new boolean[nums.length]);
        return answer;
    }

    private void backtrack(final List<List<Integer>> answer, final List<Integer> tempList, final int[] nums,
                           final boolean[] used) {
        if (tempList.size() == nums.length) {
            answer.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(answer, tempList, nums, new boolean[nums.length]);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PermuteUnique sol = new PermuteUnique();
        final List<List<Integer>> answer = sol.permuteUnique(new int[]{1, 2, 2});
        for (List<Integer> nums : answer) {
            System.out.println(Arrays.toString(new List[]{nums}));
        }
    }
}
