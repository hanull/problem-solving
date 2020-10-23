package Programmers.solution43163;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        String begin = "hit";
        String target = "hhh";
        String[] words = {"hhh", "hht"};
        System.out.println(sol.solution(begin, target, words));
    }
}

class Solution {

    static HashSet<Character> dict = new HashSet<>();
    static HashSet<String> visited = new HashSet<>();

    public int solution(String begin, String target, String[] words) {
        makeDict(words);
        int res = bfs(begin, target, words);
        return res;
    }

    private int bfs(String begin, String target, String[] words) {
        int res = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(begin, 0));
        visited.add(begin);
        while (!q.isEmpty()) {
            Pair tmp = q.poll();
            String word = tmp.word;
            int cnt = tmp.cnt;
            if (word.equals(target)) {
                res = cnt;
                break;
            }
            for (int i = 0; i < word.length(); i++) {
                for (char ch : dict) {
                    String pre = word.substring(0, i) + ch;
                    String post = word.substring(i + 1, word.length());
                    String newWord = pre.concat(post);
                    if (visited.contains(newWord)) continue;
                    if (!isContain(newWord, words)) continue;
                    visited.add(newWord);
                    q.add(new Pair(newWord, cnt + 1));
                }
            }
        }
        return res;
    }

    private boolean isContain(String newWord, String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(newWord)) {
                return true;
            }
        }
        return false;
    }

    private void makeDict(String[] words) {
        for (String str : words) {
            for (char ch : str.toCharArray()) {
                dict.add(ch);
            }
        }
    }
}

class Pair {
    String word;
    int cnt;

    public Pair(String word, int cnt) {
        this.word = word;
        this.cnt = cnt;
    }
}