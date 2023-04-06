package Programmers.파일명정렬;

import java.util.*;

public class Solution {

    static class Node {
        int no;
        String value, head;
        int number;

        public Node(final int no, final String value, final String head, final int number) {
            this.no = no;
            this.value = value;
            this.head = head;
            this.number = number;
        }
    }

    public String[] solution(String[] files) {
        List<Node> nodes = new ArrayList<>();
        int index = 0;
        for (String file : files) {
            nodes.add(new Node(index++,
                    file,
                    splitHead(file),
                    splitNumber(file)));
        }
        nodes.sort((o1, o2) -> {
            if (o1.head.compareTo(o2.head) == 0) {
                if (o1.number == o2.number) {
                    return o1.no - o2.no;
                }
                return o1.number - o2.number;
            }
            return o1.head.compareTo(o2.head);
        });

        String[] answer = new String[files.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = nodes.get(i).value;
        }
        return answer;
    }

    private String splitHead(final String file) {
        int index = 0;
        for (int i = 0; i < file.length(); i++) {
            if (Character.isDigit(file.charAt(i))) {
                index = i;
                break;
            }
        }
        return file.substring(0, index).toLowerCase();
    }

    private int splitNumber(final String file) {
        int start = -1;
        int end = -1;
        for (int i = 0; i < file.length(); i++) {
            if (Character.isDigit(file.charAt(i))) {
                if (start == -1) {
                    start = i;
                }
            }
            if (start != -1 && !Character.isDigit(file.charAt(i))) {
                end = i;
                break;
            }
        }
        if (end == -1) {
            end = file.length();
        }
        return Integer.parseInt(file.substring(start, end));
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(
                new String[]{"foo010bar020.zip", "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})));
        System.out.println(Arrays.toString(sol.solution(
                new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"})));
    }
}
