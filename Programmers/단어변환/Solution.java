package Programmers.단어변환;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static class Node {
        int index, count;
        public Node(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }

    public int solution(String begin, String target, String[] words) {
        int answer = words.length;
        for (int i=0; i<words.length; i++) {
            if (isPossibleNextWord(begin, words[i])) {
                answer = Math.min(answer, changeWord(target, words, i));
                if (answer == 1) break;
            }
        }
        return answer == words.length ? 0 : answer;
    }

    private int changeWord(String target, String[] words, int start) {
        int answer = words.length;
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        q.add(new Node(start, 1));
        visited[start] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int current = node.index;
            int count = node.count;
            if (words[current].equals(target)) {
                answer = count;
                break;
            }
            for (int i=0; i<words.length; i++) {
                if (visited[i]) continue;
                if (isPossibleNextWord(words[current], words[i])) {
                    q.add(new Node(i, count + 1));
                    visited[i] = true;
                }
            }
        }
        return answer;
    }

    private boolean isPossibleNextWord(String begin, String target) {
        int count = 0;
        for (int i=0; i<begin.length(); i++) {
            if (begin.charAt(i) != target.charAt(i)) count++;
            if (count > 1) return false;
        }
        return count == 1;
    }
}
