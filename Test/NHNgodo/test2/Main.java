package Test.NHNgodo.test2;

public class Main {

    public static void main(String[] args) {
        int page = 5457;
        int[] broken = {6,7,8};
        Solution sol = new Solution();
        System.out.println(sol.solution(page, broken));
    }
}

//핵심 소스코드의 설명을 주석으로 작성하면 평가에 큰 도움이 됩니다.
class Solution{
    public int solution(int page, int[] broken){
        boolean[] button = new boolean[10];
        for (int i = 0; i < broken.length; i++) {
            button[broken[i]] = true;
        };
        int min = Math.min(Math.abs(page - 100), move(page, button));
        return min;
    }

    private static int move(int channel, boolean[] button) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= 1000000; i++) {
            if (isPossible(i, button)) {
                int len = String.valueOf(i).length();
                int total = len + Math.abs(channel - i);
                if (total < res) res = total;
            }
        }
        return res;
    }

    private static boolean isPossible(int num, boolean[] button) {
        String tmp = String.valueOf(num);
        for (int i = 0; i < tmp.length(); i++) {
            if (button[tmp.charAt(i) - '0'])
                return false;
        }
        return true;
    }
}