package SWEA.암호문1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

    static int N, M;
    static LinkedList<Integer> list;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= 10; tc++) {
            list = new LinkedList<>();
            N = stoi(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(stoi(st.nextToken()));
            }
            M = stoi(br.readLine());
            StringTokenizer command = new StringTokenizer(br.readLine(), "I");
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(command.nextToken());
                int idx = stoi(st.nextToken());
                int cnt = stoi(st.nextToken());
                for (int j = 0; j < cnt; j++) {
                    list.add(idx++, stoi(st.nextToken()));
                }
            }
            print(tc);
        }
    }

    static void print(int tc) {
        System.out.print("#" + tc + " ");
        for (int i = 0; i < 10; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
