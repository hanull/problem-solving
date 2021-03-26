package boj.boj2224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] vertexList = new ArrayList[52];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = stoi(br.readLine());
        for (int i = 0; i < 52; i++) {
            vertexList[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char leftAlphabet = st.nextToken().charAt(0); st.nextToken();
            char rightAlphabet = st.nextToken().charAt(0);
            int from = changeToNumber(leftAlphabet);
            int to = changeToNumber(rightAlphabet);
            vertexList[from].add(to);
        }

        findAllTrueProposition();
    }

    static void findAllTrueProposition() {
        StringBuilder result = new StringBuilder();
        int totalCount = 0;

        for (int startVertex = 0; startVertex < 52; startVertex++) {
            int[] distance = new int[52];
            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(startVertex);

            while (!deque.isEmpty()) {
                int currentVertex = deque.poll();
                for (int nextVertex : vertexList[currentVertex]) {
                    if (distance[nextVertex] == 1) continue;
                    distance[nextVertex] = 1;
                    deque.add(nextVertex);
                }
            }

            char startAlphabet = (char) (startVertex < 26 ? 'A' + startVertex : 'a' + startVertex - 26);
            for (int i = 0; i < 26; i++) {
                if (startVertex != i && distance[i] == 1) {
                    totalCount++;
                    result.append(startAlphabet);
                    result.append(" => ");
                    result.append((char) (i < 26 ? 'A' + i : 'a' + i - 26));
                    result.append("\n");
                }
            }
            for (int i = 26; i < 52; i++) {
                if (startVertex != i && distance[i] == 1) {
                    totalCount++;
                    result.append(startAlphabet);
                    result.append(" => ");
                    result.append((char) (i < 26 ? 'A' + i : 'a' + i - 26));
                    result.append("\n");
                }
            }
        }
        System.out.println(totalCount);
        System.out.print(result);
    }

    static int changeToNumber(char alphabet) {
        if (alphabet >= 'A' && alphabet <= 'Z') {
            return alphabet - 'A';
        }
        return alphabet - 'a' + 26;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
