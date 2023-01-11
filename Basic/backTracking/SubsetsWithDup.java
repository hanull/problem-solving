package Basic.backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * contains duplicates
 */
public class SubsetsWithDup {

    public List<List<Integer>> subsetWithDup(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(answer, new ArrayList<>(), nums, 0);
        return answer;
    }

    private void backtrack(final List<List<Integer>> answer, final List<Integer> tempList, final int[] nums,
                           final int start) {
        answer.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i - 1] == nums[i]) {
                continue;
            }
            tempList.add(nums[i]);
            backtrack(answer, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        SubsetsWithDup sol = new SubsetsWithDup();
        final List<List<Integer>> answer = sol.subsetWithDup(new int[]{1, 2, 2});
        for (List<Integer> nums : answer) {
            System.out.println(Arrays.toString(new List[]{nums}));
        }
    }
}
