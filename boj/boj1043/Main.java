package boj.boj1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
6 5
1 6
2 4 5
2 1 2
2 2 3
2 3 4
2 5 6
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int count = stoi(st.nextToken());
        if (count == 0) {
            System.out.println(M);
            System.exit(0);
        }

        Set<Integer> virus = new HashSet<>();
        for (int i = 0; i < count; i++) {
            virus.add(stoi(st.nextToken()));
        }

        List<Integer>[] party = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            party[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            count = stoi(st.nextToken());
            for (int j = 0; j < count; j++) {
                party[i].add(stoi(st.nextToken()));
            }
        }

        boolean[] partyFlag = new boolean[M];
        Arrays.fill(partyFlag, true);
        while (true) {
            boolean breakFlag = true;
            for (int i = 0; i < M; i++) {
                boolean flag = true;
                for (int people : party[i]) {
                    if (partyFlag[i] && virus.contains(people)) {
                        flag = false;
                        partyFlag[i] = false;
                        break;
                    }
                }
                if (!flag) {
                    breakFlag = false;
                    for (int people : party[i]) {
                        virus.add(people);
                    }
                }
            }
            if (breakFlag) break;
        }

        int result = 0;
        for (int i = 0; i < M; i++) {
            if (partyFlag[i]) result++;
        }
        System.out.println(result);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
