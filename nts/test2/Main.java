package nts.test2;

/*
Run-Length Encoding은 문자열에서 같은 값이 연속해서 나타나는 것을 그 개수와 반복되는 값으로 표현하는 방법입니다.
즉, 문자가 반복되면, 반복되는 문자의 갯수로 치환하는 것을 말합니다.
ex) AAAAABBBBBBXQQQPP  ->  5A6BX3Q2P
* 문자가 하나만 올 경우는 1X가 아니라 X 형태로 원문대로 치환됩니다.

이 알고리즘의 decoding 함수를 구현하시오.
runLengthDecoding(“5A6BX3Q2P”)  ->  “AAAAABBBBBBXQQQPP”
숫자는 int 이하의 값, 문자는 대문자만 입력되며, 입력값에 대한 validation check는 하지 않습니다.

public String runLengthDecoding(String input) {
    String result = "";
}
 */

public class Main {

    static public String runLengthDecoding(String input) {
        String result = "";

        int idx = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                int num = 0;
                char ch = input.substring(idx, i).charAt(0);
                if (ch >= 'A' && ch <= 'Z') {
                    num = 1;
                } else {
                    num = Integer.parseInt(input.substring(idx, i));
                }

                idx = i + 1;

                for (int j = 0; j < num; j++) {
                    result += input.charAt(i);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(runLengthDecoding("5A6BX3Q2P"));
    }
}
