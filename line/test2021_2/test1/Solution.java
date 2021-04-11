package line.test2021_2.test1;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    static HashMap<String, String> flagRules;

    static public boolean[] solution(String program, String[] flag_rules, String[] commands) {
        boolean[] answer = new boolean[commands.length];

        // flag_rules 저장
        flagRules = new HashMap<>();
        saveFlagRules(flag_rules);

        for (int index = 0; index < commands.length; index++) {
            answer[index] = isValidCommand(program, commands[index]);
        }
        return answer;
    }

    static boolean isValidCommand(String program, String command) {
        // 확인할 명령어 저장
        String[] commandData = command.split(" ");

        // 1. 프로그램명이 같은지 확인
        if (!isCorrectProgramName(program, commandData[0])) return false;

        // 2. command 입력 형식이 올바른치 확인
        if (!isValidFlag(commandData)) return false;

        return true;
    }

    static boolean isValidFlag(String[] commandData) {
        for (int index = 1; index < commandData.length; index += 2) {
            String flag = commandData[index];

            if (!flagRules.containsKey(flag)) return false; // 대응하는 flag가 없는 경우

            if (flagRules.get(flag).equals("STRING")) {     // String : flag argument 대,소문자로만 구성되어 있는지 확인
                if (index + 1 >= commandData.length || !isString(commandData[index + 1])) return false;

            } else if (flagRules.get(flag).equals("NUMBER")) {  // NUMBER : flag argument 숫자로만 구성되어 있는지 확인
                if (index + 1 >= commandData.length || !isDigit(commandData[index + 1])) return false;

            } else if (flagRules.get(flag).equals("NULL")) {    // NULL : flag argument 뒤에 값이 있는지 확인
                if (index < commandData.length - 1) {   // NULL flag 뒤에 다른 명령이 존재할 경우
                    // 다음 명령어는 반드시 flag를 나타내는 '-' 문자가 와야한다.
                    if (!commandData[index + 1].contains("-")) return false;
                }
                --index;
            }
        }
        return true;
    }

    static boolean isString(String input) {
        for (char ch : input.toCharArray()) {
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) continue;
            else return false;
        }
        return true;
    }

    static boolean isDigit(String input) {
        for (char ch : input.toCharArray()) {
            if (ch < '0' || ch > '9') return false;
        }
        return true;
    }

    static void saveFlagRules(String[] flag_rules) {
        for (String rule : flag_rules) {
            if (flagRules.containsKey(rule)) continue;
            String[] temporarySpace = rule.split(" ");
            flagRules.put(temporarySpace[0], temporarySpace[1]);
        }
    }

    static boolean isCorrectProgramName(String targetName, String inputName) {
        return targetName.equals(inputName);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("line", new String[]{"-s STRING", "-n NUMBER", "-e NULL"}, new String[]{"line -n 100 -s hi -e", "lien -s Bye"})));
        System.out.println(Arrays.toString(solution("line", new String[]{"-s STRING", "-n NUMBER", "-e NULL"}, new String[]{"line -s 123 -n HI", "line fun"})));
    }
}
