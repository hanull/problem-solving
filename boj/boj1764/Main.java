package boj.boj1764;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        HashSet<String> peoples = new HashSet<>();
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String people = br.readLine();
            if (!peoples.contains(people)) peoples.add(people);
        }
        for (int i = 0; i < M; i++) {
            String people = br.readLine();
            if (peoples.contains(people)) res.add(people);
        }
        Collections.sort(res);
        StringBuilder sb = new StringBuilder();
        sb.append(res.size() + "\n");
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i) + "\n");
        }
        System.out.println(sb.toString());
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
