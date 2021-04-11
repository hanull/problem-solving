package line.test2021.test1;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

    static class Node {
        String language;
        int weight;

        public Node(String language, int weight) {
            this.language = language;
            this.weight = weight;
        }
    }

    static public String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.weight == o2.weight) {
                    return o1.language.compareTo(o2.language);
                }
                return o2.weight - o1.weight;
            }
        });

        String[] jobList = new String[5];
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(table[i]);
            jobList[i] = st.nextToken();
            int languagesIndex = 0;
            int total = 0;
            while (st.hasMoreTokens()) {
                String targetLanguage = st.nextToken();
                for (int j = 0; j < languages.length; j++) {
                    if (targetLanguage.equals(languages[j])) {
                        total += (5 - languagesIndex) * (preference[j]);
                    }
                }
                pq.add(new Node(jobList[i], total));
                languagesIndex++;
            }

        }
        return pq.poll().language;
    }

    public static void main(String[] args) {
        String[] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
        String[] languages = {"PYTHON", "C++", "SQL"};
        int[] preference = {7, 5, 5};
        System.out.println(solution(table, languages, preference));
    }
}
