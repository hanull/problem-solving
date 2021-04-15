package Test.kakao.test2;

public class Solution {

    static int[] selectedRobot;
    static int answer;

    public int solution(int[][] needs, int r) {
        selectedRobot = new int[r];
        int productLength = needs[0].length;
        buyRobot(0, 0, r, productLength, needs);

        return answer;
    }

    public void buyRobot(int cnt, int start, int targetCount, int productLength, int[][] needs) {
        if (cnt == targetCount) {
            answer = Math.max(answer, calculate(needs));
            return;
        }

        for (int i = start; i < productLength; i++) {
            selectedRobot[cnt] = i;
            buyRobot(cnt + 1, i + 1, targetCount, productLength, needs);
        }

    }

    public int calculate(int[][] needs) {
        int total = 0;
        for (int i = 0; i < needs.length; i++) {
            boolean flag = true;
            for (int j = 0; j < needs[0].length; j++) {
                if (needs[i][j] == 1 && !hasProduct(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) total++;
        }
        return total;
    }

    boolean hasProduct(int number) {
        for (int robot : selectedRobot) {
            if (number == robot) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] needs = {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 1, 1}};
        int r = 2;
        System.out.println(sol.solution(needs, r));
    }
}
