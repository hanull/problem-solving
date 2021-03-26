package boj.boj2224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static boolean[][] vertexList = new boolean[52][52];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = stoi(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char leftAlphabet = st.nextToken().charAt(0); st.nextToken();
            char rightAlphabet = st.nextToken().charAt(0);
            int from = characterToInt(leftAlphabet);
            int to = characterToInt(rightAlphabet);
            vertexList[from][to] = true;
        }

        findAllTrueProposition();
    }

    static void findAllTrueProposition() {
        StringBuilder result = new StringBuilder();
        int totalCount = 0;

        for (int mid = 0; mid < 52; mid++) {
            for (int from = 0; from < 52; from++) {
                for (int to = 0; to < 52; to++) {
                    if (vertexList[from][mid] && vertexList[mid][to]) {
                        vertexList[from][to] = true;
                    }
                }
            }
        }


        for (int from = 0; from < 52; from++) {
            char startAlphabet = intToCharacter(from);
            for (int to = 0; to < 52; to++) {
                if (from == to) continue;
                if (vertexList[from][to]) {
                    totalCount++;
                    result.append(startAlphabet);
                    result.append(" => ");
                    result.append(intToCharacter(to));
                    result.append("\n");
                }
            }
        }

        System.out.println(totalCount);
        System.out.print(result);
    }

    static int characterToInt(char alphabet) {
        if (alphabet >= 'A' && alphabet <= 'Z') {
            return alphabet - 'A';
        }
        return alphabet - 'a' + 26;
    }

    static char intToCharacter(int number) {
        if (number < 26) {
            return (char) (number + 'A');
        }
        return (char) ('a' + number - 26);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
