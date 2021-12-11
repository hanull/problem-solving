package Test.linefintech.test3;

public class Solution {

	static final String[][] NUMBERS_PIXELS = {{"111", "101", "101", "101", "111"},
		{"110", "010", "010", "010", "111"},
		{"111", "001", "111", "100", "111"},
		{"111", "001", "111", "001", "111"},
		{"101", "101", "111", "001", "001"},
		{"111", "100", "111", "001", "111"},
		{"111", "100", "111", "101", "111"},
		{"111", "101", "001", "001", "001"},
		{"111", "101", "111", "101", "111"},
		{"111", "101", "111", "001", "111"}};
	static final String[] NUMBERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

	public String solution(String[] pixels) {
		StringBuilder answer = new StringBuilder();
		int len = pixels[0].length();
		for (int i = 0; i < len; i += 3) {
			String[] targetNumber = new String[5];
			for (int j = 0; j < 5; j++) {
				targetNumber[j] = pixels[j].substring(i, i + 3);
			}
			String number = checkNumber(targetNumber);
			answer.append(number);
		}
		return answer.toString();
	}

	private String checkNumber(String[] targetNumber) {
		for (int i = 0; i < 10; i++) {
			boolean flag = true;
			for (int j = 0; j < 5; j++) {
				if (!NUMBERS_PIXELS[i][j].equals(targetNumber[j])) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return NUMBERS[i];
			}
		}
		return "";
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution(
			new String[] {"111111111111111111111111110111111111", "001101001101101100101101010101001100",
				"111101111101101111101111010111111111", "100101100101101101101001010101001001",
				"111111111111111111111111111111111111"}));
		System.out.println(sol.solution(
			new String[] {"110111101111111111110111", "010101101100101101010100", "010111111111101111010111",
				"010001001001101101010001", "111111001111111111111111"}));
		System.out.println(sol.solution(
			new String[] {"111110111101111101111101", "100010101101001101100101", "111010111111111111111111",
				"001010101001100001001001", "111111111001111001111001"}));
	}
}
