package boj.boj4195;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;
    static int[] level;
    static HashMap<String, Integer> hashMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        while (T-- > 0) {
            int N = stoi(br.readLine());
            parent = new int[N * 2 + 1];
            level = new int[N * 2 + 1];
            for (int i = 1; i <= 2 * N; i++) {
                parent[i] = i;
                level[i] = 1;
            }
            hashMap = new HashMap<>();
            int idx = 1;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String friendA = st.nextToken();
                String friendB = st.nextToken();
                if (!hashMap.containsKey(friendA)) {
                    hashMap.put(friendA, idx++);
                }
                if (!hashMap.containsKey(friendB)) {
                    hashMap.put(friendB, idx++);
                }
                union(hashMap.get(friendA), hashMap.get(friendB));
                System.out.println(level[find(hashMap.get(friendA))]);
            }
        }
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;
        if (a < b) {
            parent[b] = a;
            level[a] += level[b];
        } else {
            parent[a] = b;
            level[b] += level[a];
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
