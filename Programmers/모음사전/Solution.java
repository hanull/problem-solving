package Programmers.모음사전;

public class Solution {

    static final char[] alphabet = {'A', 'E', 'I', 'O', 'U'};
    static final int N = 5;
    static int no, answer;

    public int solution(String word) {
        dfs(0, "", word);
        return answer;
    }

    private void dfs(int index, String word, String target) {
        if (index == N) return;
        for (int i = 0; i < N; i++) {
            String newWord = word + alphabet[i];
            no++;
            if (isSame(newWord, target)) {
                answer = no;
                return;
            }
            dfs(index + 1, newWord, target);
        }
    }

    private boolean isSame(String word, String target) {
        return word.equals(target);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("AAAAE"));
    }

}
