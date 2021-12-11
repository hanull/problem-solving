package Test.linefintech.test5;

public class Solution {

	public int solution(String[] strs) {
		int answer = 0;
		String[] pattern = {"AAB+", "BAB+A"};
		for (String str : strs) {
			if (isMatch(pattern, str)) {
				answer++;
			}
		}
		return answer;
	}

	private boolean isMatch(String[] pattern, String str) {
		String tmp = String.copyValueOf(str.toCharArray());
		for (int i = 0; i < 2; i++) {
			tmp = tmp.replaceAll(pattern[i], "");
		}
		if (tmp.equals("")) {
			return true;
		}
		tmp = String.copyValueOf(str.toCharArray());
		for (int i = 1; i >= 0; i--) {
			tmp = tmp.replaceAll(pattern[i], "");
		}
		if (tmp.equals("")) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution(new String[] {"AABAAA", "BABABB", "BABBAAAB", "BABAAABAABBABBA"}));
		System.out.println(sol.solution(new String[] {"AA", "BAB", "BAAAA", "ABBABB", "AABBBBABBAAAA"}));
		System.out.println(sol.solution(new String[] {"AABAABAAB", "AABBBAABBB", "AABBBABBABABBBAAABBBABBBA"}));
	}
}
