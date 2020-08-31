package ebay.test2;

public class Main {

    static int min = 10001;

    public static void main(String[] args) {
        int num = 18;
        int[] cards = {1, 2, 5};
        System.out.println(solution(num, cards));
    }

    public static int solution(int num, int[] cards) {
        int end = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == num) {
                min = 1;
            } else if (cards[i] < num) {
                end = i;
            } else {
                break;
            }
        }
        if (min != 1) {
            dfs(0, 0, end, num, cards);
        }
        min = min == 10001 ? -1 : min;
        return min;
    }

    private static void dfs(int hap, int cnt, int end, int target, int[] cards) {
        if (cnt >= min) {
            return;
        }
        if (hap == target) {
            min = min > cnt ? cnt : min;
            return;
        } else if (hap > target) {
            return;
        } else {
            for (int i = 0; i <= end; i++) {
                dfs(hap + cards[i], cnt + 1, end, target, cards);
            }
        }
    }
}
