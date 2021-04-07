package boj.boj1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1 5
antatica
1

1 7
antabbtica
output: 0
correct answer: 1

2 7
antaatica
antabtica
output: 0
correct answer: 2

1 7
antabctica
output: 0
correct answer: 1

6 6
antabcdefgtica
antabcdefgtica
antabcdefgtica
antabcdefgtica
antabcdefgtica
antaztica
1
 */

public class Main {

    static int N, K, maxCount;
    static char[] learnLetters;
    static List<Character> letterList = new ArrayList<>();
    static Set<Character> basicLetter = new HashSet<>();
    static String[] inputWord;
    static {
        basicLetter.add('a');
        basicLetter.add('c');
        basicLetter.add('i');
        basicLetter.add('n');
        basicLetter.add('t');
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        inputWord = new String[N];

        if (K < 5) {
            System.out.println(0);
            System.exit(0);
        } else if (K == 26) {
            System.out.println(N);
            System.exit(0);
        }

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            inputWord[i] = word;
            int wordLength = word.length();
            for (int j = 4; j < wordLength - 4; j++) {
                if (!letterList.contains(word.charAt(j)) && !basicLetter.contains(word.charAt(j))) {
                    letterList.add(word.charAt(j));
                }
            }
        }
        // *** 틀렸던 예외 ****
        // K = 6이고, 단어가 "antatica" 일 경우,
        // 가르칠 수 있는 문자의 개수가 배워야할 문자의 개수보다 크다는 것을 의미한다.
        // 따라서, N개의 단어 모두 읽을 수 있다.
        K -= 5;
        if (letterList.size() < K) maxCount = N;
        else {
            learnLetters = new char[K];
            comb(0, 0);
        }
        System.out.println(maxCount);
    }

    static void comb(int cnt, int start) {
        if (cnt == K) {
            maxCount = Math.max(maxCount, calculate());
            return;
        }

        for (int i = start; i < letterList.size(); i++) {
            learnLetters[cnt] = letterList.get(i);
            comb(cnt + 1, i + 1);
        }
    }

    static int calculate() {
        int total = 0;
        for (String word : inputWord) {
            boolean isPossible = true;
            if (word.length() == 8) {
                if (word.equals("antatica")) {
                    total++;
                }
                continue;
            }
            for (int i = 4; i < word.length() - 4; i++) {
                char ch = word.charAt(i);
                if (basicLetter.contains(ch)) continue;

                boolean flag = false;
                for (int j = 0; j < K; j++) {
                    if (learnLetters[j] == ch) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) total++;
        }
        return total;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
