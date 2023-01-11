package Basic.backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permute {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        // Arrays.sort(nums); not necessary
        backtrack(answer, new ArrayList<>(), nums);
        return answer;
    }

    private void backtrack(final List<List<Integer>> answer, final List<Integer> tempList, final int[] nums) {
        if (tempList.size() == nums.length) {
            answer.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) {
                    continue;
                }
                tempList.add(nums[i]);
                backtrack(answer, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Permute sol = new Permute();
        final List<List<Integer>> answer = sol.permute(new int[]{1, 2, 3});
        for (List<Integer> nums : answer) {
            System.out.println(Arrays.toString(new List[]{nums}));
        }
    }
}
