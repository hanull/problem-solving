package Test.woowa.test3;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] expected = {"H", "T", "H", "T", "H", "T", "H"};
        String[] actual = {"T", "T", "H", "H", "T", "T", "H"};
        System.out.println(sol.solution(1000, expected, actual));
    }
}

class Solution {
    public int solution(int money, String[] expected, String[] actual) {
        int betAmount = 100;
        for (int i = 0; i < expected.length; i++) {
            if (money == 0) break;
            if (isWin(expected[i], actual[i])) {
                money += betAmount;
                betAmount = 100;
            } else {
                money -= betAmount;
                if (money < betAmount * 2) {
                    betAmount = money;
                } else {
                    betAmount *= 2;
                }
            }
        }
        return money;
    }

    private boolean isWin(String a, String b) {
        return a.equals(b) ? true : false;
    }
}