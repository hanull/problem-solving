package Test.delivery.test1;

import java.util.HashMap;

public class Solution {

    public String solution(String S, String C) {
        StringBuilder answer = new StringBuilder();
        HashMap<String, Integer> checkName = new HashMap<>();
        String[] input = S.split(";");
        C = C.toLowerCase();
        for (int index = 0; index < input.length; index++) {
            String[] fullName = input[index].trim().split(" ");
            String firstName = fullName[0];
            String lastName = getLastName(fullName[fullName.length - 1]);
            String newName = firstName.concat(".").concat(lastName).toLowerCase();
            if (checkName.containsKey(newName)) {
                int count = checkName.get(newName) + 1;
                checkName.put(newName, count);
                newName = newName.concat(String.valueOf(count));
            } else {
                checkName.put(newName, 1);
            }
            answer.append(input[index].trim()).append(" <").append(newName.concat("@").concat(C).concat(".com")).append(">");
            if (index < input.length - 1) answer.append("; ");
        }
        return answer.toString();
    }

    private String getLastName(String input) {
        input = input.replace("-", "");
        return input.length() > 8 ? input.substring(0, 8) : input;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; John Evan Doe; Jane Doe; Peter Brian Parker",
                "Example").equals(
                        "John Doe <john.doe@example.com>; Peter Benjamin Parker <peter.parker@example.com>; Mary Jane Watson-Parker <mary.watsonpa@example.com>; John Elvis Doe <john.doe2@example.com>; John Evan Doe <john.doe3@example.com>; Jane Doe <jane.doe@example.com>; Peter Brian Parker <peter.parker2@example.com>"
        ));
    }
}
