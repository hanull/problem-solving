package Basic.싸피특강.연속된N;

/*
연속된 N개의 구간에서
어떤 알파벳이 가장 많이 등장하는가?
 */

public class Main {

    static int N = 5;
    static char[] input = {'s', 's', 'a', 'd', 'a', 'd', 'a', 'a', 'a', 's', 'a', 'd', 'a', 'a', 'a', 's'};
    static int count;
    static char alpha;


    public static void main(String[] args) {
//        solution1();
        solution2();    // 슬로우윈도우
        System.out.println(alpha + " " + count);
    }

    static void solution1() {
        count = 0;
        for (int i = 0; i <= input.length - N; i++) {
            int[] countArray = new int[26];
            for (int j = i; j < i + N; j++) {
                countArray[input[j] - 'a']++;
                if (countArray[input[j] - 'a'] > count) {
                    count = countArray[input[j] - 'a'];
                    alpha = input[j];
                }
            }
        }
    }

    private static void solution2() {
        count = 0;
        int[] countArray = new int[26];
        for (int i = 0; i < N; i++) {
            countArray[input[i] - 'a']++;
            if (countArray[input[i] - 'a'] > count) {
                count = countArray[input[i] - 'a'];
                alpha = input[i];
            }
        }
        for (int i = 0; i < input.length - N; i++) {
            countArray[input[i] - 'a']--;
            if (countArray[input[i] - 'a'] > count) {
                count = countArray[input[i] - 'a'];
                alpha = input[i];
            }
            countArray[input[i + N] - 'a']++;
            if (countArray[input[i + N] - 'a'] > count) {
                count = countArray[input[i + N] - 'a'];
                alpha = input[i + N];
            }
        }
    }
}
