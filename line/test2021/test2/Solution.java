package line.test2021.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {

    static final String specialCharacters = "~!@#$%^&*";
    static HashMap<Character, Integer> hashMap;
    static boolean[] includeCharacterCheck;   // 대문자, 소문자, 숫자, 특수문자

    static public int[] solution(String inp_str) {
        boolean[] checkFailList = new boolean[6];
        hashMap = new HashMap<>();
        includeCharacterCheck = new boolean[4];

        // 1. input 길이
        if (inp_str.length() < 8 || inp_str.length() > 15) checkFailList[1] = true;

        int consecutiveFlag = 0;
        char checkCharacter = ' ';
        for (char ch : inp_str.toCharArray()) {
            if (!hashMap.containsKey(ch)) {
                hashMap.put(ch, 1);
            } else {
                hashMap.put(ch, hashMap.get(ch) + 1);
            }
            // 2. 4종류의 문자 그룹을 제외한, 다른 어떤 문자도 포함 불가
            if (!isAvailable(ch)) checkFailList[2] = true;
            if (ch != checkCharacter) {
                checkCharacter = ch;
                consecutiveFlag = 1;
            } else {
                consecutiveFlag++;
            }
            // 4. 같은 문자가 4번 이상 반복될 수 없음.
            if (consecutiveFlag >= 4) checkFailList[4] = true;
        }

        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if (includeCharacterCheck[i]) cnt++;
        }

        // 3. 4종류의 문자그룹 중 3종류 이상 포함해야함
        if (cnt < 3) checkFailList[3] = true;

        // 5. 같은 문자의 개수가 5개 이상 포함될 수 없음
        for (char ch : inp_str.toCharArray()) {
            if (hashMap.get(ch) >= 5) {
                checkFailList[5] = true;
            }
        }

        List<Integer> failList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            if (checkFailList[i]) failList.add(i);
        }

        int[] result;
        if (failList.size() == 0) {
            result = new int[1];
            result[0] = 0;
        } else {
            result = new int[failList.size()];
            for (int i = 0; i < failList.size(); i++) {
                result[i] = failList.get(i);
            }
        }
        return result;
    }

    static boolean isAvailable(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            includeCharacterCheck[0] = true;
            return true;
        }
        if (ch >= 'a' && ch <= 'z') {
            includeCharacterCheck[1] = true;
            return true;
        }
        if (ch >= '0' && ch <= '9') {
            includeCharacterCheck[2] = true;
            return true;
        }
        if (specialCharacters.contains(String.valueOf(ch))) {
            includeCharacterCheck[3] = true;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("AaTa+!12-3")));
        System.out.println(Arrays.toString(solution("aaaaZZZZ)")));
        System.out.println(Arrays.toString(solution("CaCbCgCdC888834A")));
        System.out.println(Arrays.toString(solution("UUUUU")));
        System.out.println(Arrays.toString(solution("ZzZz9Z824")));
    }
}
