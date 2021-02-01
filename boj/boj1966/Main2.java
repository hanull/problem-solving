package boj.boj1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static int T, N, targetIndex;
    static PriorityQueue<Integer> weightQ;
    static Queue<Docs> docsQ;

    static class Docs {
        int idx;
        int weight;

        public Docs(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = stoi(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            targetIndex = stoi(st.nextToken());
            weightQ = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            docsQ = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int weight = stoi(st.nextToken());
                weightQ.add(weight);
                docsQ.add(new Docs(i, weight));
            }

            int order = 1;
            while (!docsQ.isEmpty()) {
                Docs docs = docsQ.poll();
                if (weightQ.peek() == docs.weight) {
                    weightQ.poll();
                    if (targetIndex == docs.idx) {
                        break;
                    }
                    order++;
                } else {
                    docsQ.add(docs);
                }
            }
            System.out.println(order);
        }

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}

