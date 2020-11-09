package Test.woowa.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] log = {"0001 3 95", "0001 5 90", "0001 5 100", "0002 3 95", "0001 7 80", "0001 8 80", "0001 10 90", "0002 10 90", "0002 7 80", "0002 8 80", "0002 5 100", "0003 99 90"};
        System.out.println(Arrays.toString(sol.solution(log)));
    }
}

class Solution {

    static HashMap<String, HashMap<Integer, Integer>> data = new HashMap<>();    // 수험번호, 문제번호, 시험점수
    static ArrayList<String> peoples = new ArrayList<>();

    public String[] solution(String[] logs) {
        for (int i = 0; i < logs.length; i++) {
            String[] input = logs[i].split(" ");
            String peopleNo = input[0];
            int testNo = Integer.valueOf(input[1]);
            int testScore = Integer.valueOf(input[2]);
            if (!peoples.contains(peopleNo)) {
                peoples.add(peopleNo);
                data.put(peopleNo, new HashMap<>());
            }
            HashMap<Integer, Integer> tmp = data.get(peopleNo);
            tmp.put(testNo, testScore);
            data.put(peopleNo, tmp);
        }
        // 5문제 미만으로 푼 사람 제거
        for (String no : peoples) {
            HashMap<Integer, Integer> tmp = data.get(no);
            if (tmp.size() < 5) {
                data.remove(no);
            }
        }
        ArrayList<String> list = new ArrayList<>();
        // 중복자 확인
        for (int i = 0; i < data.size() - 1; i++) {
            for (int j = i + 1; j < data.size(); j++) {
                // 문제 수가 다를 경우
                if (data.get(peoples.get(i)).size() != data.get(peoples.get(j)).size()) continue;
                // 문제 번호, 문제 점수
                boolean flag = true;
                for (int no : data.get(peoples.get(i)).keySet()) {
                    if (data.get(peoples.get(j)).containsKey(no) && data.get(peoples.get(i)).get(no) == data.get(peoples.get(j)).get(no)) {
                        continue;
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    if (!list.contains(peoples.get(i))) list.add(peoples.get(i));
                    if (!list.contains(peoples.get(j))) list.add(peoples.get(j));
                }
            }
        }
        Collections.sort(list);
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return list.size() == 0 ? new String[]{"None"} : res;
    }
}
