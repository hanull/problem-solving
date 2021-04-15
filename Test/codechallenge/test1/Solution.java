package Test.codechallenge.test1;

class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for (int i=0; i<absolutes.length; i++) {
            int temp = absolutes[i];
            if (!signs[i]) temp *= -1;
            answer += temp;
        }
        return answer;
    }
}
