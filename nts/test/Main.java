package nts.test;

/*
프로그래밍 언어마다 변수를 표기하기 위한 특정한 표기법을 권장한다.
단어를 밑줄로 구분하는 스네이크표기법과, 단어가 바뀔 때마다 대문자로 시작하는 카멜표기법이 대표적이다. 두가지 표기법으로 변환하는 프로그램을 구현하시오.

ex) this_is_long_variable_name 이 입력되면, thisIsLongVariableName 이라고 리턴하고,
thisIsLongVariableName 이 입력되면, this_is_long_variable_name 이라고 리턴한다.

public String changeConvection(String input){
    String result = "";
}
 */

import java.util.StringTokenizer;

public class Main {

    static public String changeConvection(String input){
        String res = "";
        if (input.contains("_")) {
            StringTokenizer st = new StringTokenizer(input, "_");
            String tmp = st.nextToken();
            res += tmp;
            while (st.hasMoreTokens()) {
                tmp = st.nextToken();
                String toUpper = tmp.substring(0, 1).toUpperCase();
                String lower = tmp.substring(1);
                res += toUpper + lower;
            }
        } else {
            for (char ch : input.toCharArray()) {
                if (ch >= 'A' && ch <= 'Z') {
                    res += "_";
                    res += String.valueOf(ch).toLowerCase();
                } else {
                    res += ch;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(changeConvection("this_is_long_variable_name"));
        System.out.println(changeConvection("thisIsLongVariableName"));
    }
}
