package Programmers.수식최대화;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

	static List<Long> numbers = new ArrayList<>();
	static List<Character> operations = new ArrayList<>();
	static Set<Character> validOperation = new HashSet<>(Arrays.asList('+', '-', '*'));
	static Map<Character, Integer> operationPoint = new HashMap<>();

	public long solution(String expression) {
		long answer = 0L;
		splitExpression(expression);
		for (int plus = 0; plus < 3; plus++) {
			for (int minus = 0; minus < 3; minus++) {
				if (minus == plus)
					continue;
				for (int multiply = 0; multiply < 3; multiply++) {
					if (multiply == plus || multiply == minus)
						continue;
					setOperationPoint(plus, minus, multiply);
					answer = Math.max(answer, Math.abs(getMaxTotal()));
				}
			}
		}
		return answer;
	}

	private long getMaxTotal() {
		// 연산자는 없고 숫자만 입력된 경우
		if (operations.size() == 0) {
			return numbers.get(0);
		}
		List<Long> numberContainer = new ArrayList<>();
		List<Character> operationContainer = new ArrayList<>();
		for (int i = 0; i < operations.size(); i++) {
			operationContainer.add(operations.get(i));
			numberContainer.add(numbers.get(i));
		}
		numberContainer.add(numbers.get(operationContainer.size()));
		while (!operationContainer.isEmpty()) {
			int maxPointIndex = findMaxPointIndex(operationContainer);
			long numberA = numberContainer.get(maxPointIndex);
			long numberB = numberContainer.get(maxPointIndex + 1);
			char operation = operationContainer.get(maxPointIndex);
			long total = calculate(numberA, numberB, operation);
			numberContainer.set(maxPointIndex, total);
			numberContainer.remove(maxPointIndex + 1);
			operationContainer.remove(maxPointIndex);
		}
		return numberContainer.get(0);
	}

	private int findMaxPointIndex(List<Character> operationContainer) {
		int point = 0;
		int index = 0;
		for (int i = 0; i < operationContainer.size(); i++) {
			int nextPoint = operationPoint.get(operationContainer.get(i));
			if (nextPoint > point) {
				point = nextPoint;
				index = i;
			}
		}
		return index;
	}

	private long calculate(long numberA, long numberB, char operation) {
		if (operation == '+') {
			return numberA + numberB;
		}
		if (operation == '-') {
			return numberA - numberB;
		}
		return numberA * numberB;
	}

	private void setOperationPoint(int plus, int minus, int multiply) {
		operationPoint.put('+', plus);
		operationPoint.put('-', minus);
		operationPoint.put('*', multiply);
	}

	private void splitExpression(String expression) {
		int start = 0;
		char[] expressions = expression.toCharArray();
		for (int i = 0; i < expression.length(); i++) {
			if (validOperation.contains(expressions[i])) {
				long number = Long.parseLong(expression.substring(start, i));
				start = i + 1;
				operations.add(expressions[i]);
				numbers.add(number);
			}
		}
		long number = Long.parseLong(expression.substring(start));
		numbers.add(number);
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		// System.out.println(sol.solution("111"));
		System.out.println(sol.solution("100-200*300-500+20"));
		// System.out.println(sol.solution("50*6-3*2"));
	}
}
