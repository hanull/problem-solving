package boj.boj16925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
4
aba
bab
a
b
ba
ba
 */
public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] origin = new String[2 * N - 2];
        List<String>[] partOfOrigin = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            partOfOrigin[i] = new ArrayList<>();
        }
        for (int i = 0; i < 2 * N - 2; i++) {
            String str = br.readLine();
            int len = str.length();
            origin[i] = str;
            partOfOrigin[len].add(str);
        }
        String answerWord = N == 2 ? origin[0] + origin[1] : findOriginString(partOfOrigin);
        System.out.println(answerWord);
        System.out.println(N == 2 ? "PS" : checkSuffixOfPrefix(origin, answerWord));
    }

    private static String findOriginString(List<String>[] partOfOrigin) {
        for (String lastPartWord : partOfOrigin[N - 1]) {
            for (String firstPartWord : partOfOrigin[1]) {
                String answerWordA = lastPartWord + firstPartWord;
                String answerWordB = firstPartWord + lastPartWord;
                if (isValidAnswer(answerWordA, partOfOrigin)) {
                    return answerWordA;
                }
                if (isValidAnswer(answerWordB, partOfOrigin)) {
                    return answerWordB;
                }
            }
        }
        return "";
    }

    private static boolean isValidAnswer(String answerWord, List<String>[] partWords) {
        for (int i = 1; i < N; i++) {
            String wordA = partWords[i].get(0);
            String wordB = partWords[i].get(1);
            if (!answerWord.contains(wordA) || !answerWord.contains(wordB)) {
                return false;
            }
            if (wordA.equals(wordB)) {
                if (answerWord.indexOf(wordA) == answerWord.lastIndexOf(wordA)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String checkSuffixOfPrefix(String[] checkStrings, String targetString) {
        StringBuilder answer = new StringBuilder();
        Set<String> set = new HashSet<>();
        for (String checkString : checkStrings) {
            if (targetString.indexOf(checkString) == 0) {
                if (!set.contains(checkString)) {
                    set.add(checkString);
                    answer.append("P");
                } else {
                    answer.append("S");
                }
            } else {
                answer.append("S");
            }
        }
        return answer.toString();
    }

}
