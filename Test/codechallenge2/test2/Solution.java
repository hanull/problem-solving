package Test.codechallenge2.test2;

import java.math.BigInteger;
import java.util.Arrays;

/*
문제 설명
양의 정수 x에 대한 함수 f(x)를 다음과 같이 정의합니다.
x보다 크고 x와 비트가 1~2개 다른 수들 중에서 제일 작은 수
예를 들어,

f(2) = 3 입니다. 다음 표와 같이 2보다 큰 수들 중에서 비트가 다른 지점이 2개 이하이면서 제일 작은 수가 3이기 때문입니다.
수	비트	다른 비트의 개수
2	000...0010
3	000...0011	1
f(7) = 11 입니다. 다음 표와 같이 7보다 큰 수들 중에서 비트가 다른 지점이 2개 이하이면서 제일 작은 수가 11이기 때문입니다.
수	비트	다른 비트의 개수
7	000...0111
8	000...1000	4
9	000...1001	3
10	000...1010	3
11	000...1011	2
 */

public class Solution {

    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        int index = 0;
        for (long number : numbers) {
            answer[index++] = getNextNumber(number);
        }
        return answer;
    }

    private long getNextNumber(long number) {
        int oneCount = BigInteger.valueOf(number).bitCount();
        long res = number + (number / 2);
        System.out.println(BigInteger.valueOf(res).TEN);
        while (Math.abs(oneCount - BigInteger.valueOf(res).bitCount()) > 2) {
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        long[] numbers = {2, 7};
        System.out.println(Arrays.toString(sol.solution(numbers)));
    }
}
