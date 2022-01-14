package Programmers.뉴스클러스터링;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	static final int NUMBER = 65536;

	public int solution(String str1, String str2) {
		List<String> left = toList(str1);
		List<String> right = toList(str2);
		List<String> union = new ArrayList<>();
		List<String> intersection = new ArrayList<>();
		for (String l : left) {
			if (right.contains(l)) {
				right.remove(l);
				intersection.add(l);
			}
			union.add(l);
		}
		for (String r : right) {
			union.add(r);
		}
		double answer;
		if (union.size() == 0) {
			answer = 1;
		} else {
			answer = (double)intersection.size() / (double)union.size();
		}
		return (int) (answer * NUMBER);
	}

	private List<String> toList(String str) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < str.length() - 1; i ++) {
			String word = str.substring(i, i + 2);
			if (!isAlpha(word)) {
				continue;
			}
			list.add(word.toLowerCase());
		}
		return list;
	}

	private boolean isAlpha(String word) {
		for (char ch : word.toCharArray()) {
			if (!Character.isAlphabetic(ch)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution("FRANCE", "french"));
		System.out.println(sol.solution("aabbc", "abbde"));
		// System.out.println(sol.solution("handshake", "shake hands"));
		// System.out.println(sol.solution("aa1+aa2", "AAAA12"));
		// System.out.println(sol.solution("E=M*C^2", "e=m*c^2"));
	}
}
