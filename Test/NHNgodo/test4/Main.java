package Test.NHNgodo.test4;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        String cardNumber = "21378";
        System.out.println(sol.solution(cardNumber));
    }
}

//핵심 소스코드의 설명을 주석으로 작성하면 평가에 큰 도움이 됩니다.
class Solution{
    public String solution(String cardNumber){
        int len = cardNumber.length();
        int total = 0;
        int start = len % 2 == 0 ? 0 : 1;
        for (int i = 0; i < len; i++) {
            if (i == start) {
                int tmp = (cardNumber.charAt(i) - '0') * 2;
                String str = String.valueOf(tmp);
                for (int j = 0; j < str.length(); j++) {
                    total += str.charAt(j) - '0';
                }
                start += 2;
            } else {
                total += cardNumber.charAt(i) - '0';
            }
        }
        System.out.println(total);
        return total % 10 == 0 ? "VALID" : "INVALID";
    }
}