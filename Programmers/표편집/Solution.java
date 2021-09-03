package Programmers.표편집;

import java.util.Arrays;
import java.util.Stack;

public class Solution {

    static class Node {
        int pre, next, current;

        public Node(int pre, int next, int current) {
            this.pre = pre;
            this.next = next;
            this.current = current;
        }
    }
    static Stack<Node> backup = new Stack<>();
    static Node[] list;
    static int current;
    static char[] answer;

    public String solution(int n, int k, String[] cmd) {
        answer = new char[n];
        Arrays.fill(answer, 'O');
        current = k;
        list = new Node[n];
        for (int i = 0; i < n; i++) {
            list[i] = new Node(i - 1, i + 1, i);
        }
        list[n - 1].next = -1;
        for (String input : cmd) {
            String[] command = input.split(" ");
            run(command);
        }
        return new String(answer);
    }

    private void run(String[] command) {
        if (command[0].equals("U")) {
            int move = stoi(command[1]);
            while (move-- > 0) {
                current = list[current].pre;
            }
        } else if (command[0].equals("D")) {
            int move = stoi(command[1]);
            while (move-- > 0) {
                current = list[current].next;
            }
        } else if (command[0].equals("C")) {
            backup.add(new Node(list[current].pre, list[current].next, current));
            answer[current] = 'X';
            if (list[current].pre == -1) {
                list[list[current].next].pre = -1;
                current = list[current].next;
            } else if (list[current].next == -1) {
                list[list[current].pre].next = -1;
                current = list[current].pre;
            } else {
                list[list[current].pre].next = list[current].next;
                list[list[current].next].pre = list[current].pre;
                current = list[current].next;
            }
        } else {
            Node node = backup.pop();
            int current = node.current;
            answer[current] = 'O';
            if (list[current].pre == -1) {
                list[list[current].next].pre = current;
            } else if (list[current].next == -1) {
                list[list[current].pre].next = current;
            } else {
                list[list[current].next].pre = current;
                list[list[current].pre].next = current;
            }
        }
    }

    private int stoi(String input) {
        return Integer.parseInt(input);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        String[] cmd2 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        System.out.println(sol.solution(n, k, cmd));
        System.out.println(sol.solution(n, k, cmd2));
    }
}
