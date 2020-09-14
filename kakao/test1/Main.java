package kakao.test1;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        String new_id = "abcdefghijklmn.p";
        System.out.println(sol.solution(new_id));
    }
}

class Solution {
    public String solution(String new_id) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        int len = new_id.length();

        for (int i = 0; i < len; i++) {
            char tmp = new_id.charAt(i);
            if (Character.isAlphabetic(tmp)) { // "알파벳"
                if (Character.isUpperCase(tmp)) // 대문자일 때, 소문자로 변환 후 추가
                    sb.append(String.valueOf(tmp).toLowerCase());
                else sb.append(tmp);
            } else if (isPossible(tmp, new String(sb))) {  // "숫자, -, _, .(맨 앞x, 연속 x)"
                sb.append(tmp);
            }
            if (sb.length() > 14) {
                break;
            }
        }
        answer = new String(sb);
        if (answer.length() > 0 && answer.charAt(answer.length() - 1) == '.')  // 맨 뒤 . 은 마지막에 제거
            answer = answer.substring(0, answer.length() - 1);
        if (answer.isEmpty()) {
            answer = "a";
        }
        if (answer.length() <= 2) {
            char tmp = answer.charAt(answer.length() - 1);
            String add = "";
            for (int i = 0; i < 3 - answer.length(); i++) {
                add += tmp;
            }
            answer += add;
        }
        return answer;
    }

    private boolean isPossible(char input, String str) {
        if (Character.isDigit(input) || input == '-' || input == '_') {
            return true;
        } else if (input == '.') {
            if (str.isEmpty() || str.charAt(str.length() - 1) == '.') {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}
