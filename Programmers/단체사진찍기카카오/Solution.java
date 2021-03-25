package Programmers.단체사진찍기카카오;


public class Solution {

    static char[] kakaoFriends;

    static int solution(int n, String[] data) {
        kakaoFriends = "ACFJMNRT".toCharArray();
        int result = 0;
        do {
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                char requestFriend = data[i].charAt(0);
                char respondFriend = data[i].charAt(2);
                char operator = data[i].charAt(3);
                int dist = data[i].charAt(4) - '0';

                int requestIndex = findIndex(requestFriend);
                int respondIndex = findIndex(respondFriend);

                int interval = Math.abs(requestIndex - respondIndex) - 1;

                if (operator == '=') {
                    if (interval != dist) {
                        flag = false;
                        break;
                    }
                } else if (operator == '>') {
                    if (interval <= dist) {
                        flag = false;
                        break;
                    }
                } else {
                    if (interval >= dist) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) result++;
        } while (nextPermutation());

        return result;
    }

    static int findIndex(char target) {
        for (int i = 0; i < 8; i++) {
            if (kakaoFriends[i] == target) return i;
        }
        return -1;
    }

    static boolean nextPermutation() {
        int i = 7;
        while (i > 0 && kakaoFriends[i-1] >= kakaoFriends[i]) i--;

        if (i == 0) return false;

        int j = 7;
        while (kakaoFriends[i-1] >= kakaoFriends[j]) j--;

        swap(i - 1, j);

        j = 7;
        while (i < j) {
            swap(i++, j--);
        }

        return true;
    }

    static void swap(int i, int j) {
        char temp = kakaoFriends[i];
        kakaoFriends[i] = kakaoFriends[j];
        kakaoFriends[j] = temp;
    }

    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        String[] data2 = {"M~C<2", "C~M>1"};
        System.out.println(solution(n, data));
    }
}

