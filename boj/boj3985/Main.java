package boj.boj3985;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = stoi(br.readLine());
        int N = stoi(br.readLine());

        int[] cake = new int[L + 1];
        int no = 0;
        int total = 0;
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = stoi(st.nextToken());
            int e = stoi(st.nextToken());
            int cnt = e - s + 1;
            if (cnt > total) {
                total = cnt;
                no = i;
            }
            for (int j = s; j <= e; j++) {
                if (cake[j] == 0) {
                    cake[j] = i;
                }
            }
        }

        int[] people = new int[N + 1];
        for (int i = 1; i <= L; i++) {
            people[cake[i]]++;
        }

        int cnt = 0;
        int maxNo = 0;
        for (int i = 1; i <= N; i++) {
            if (people[i] > cnt) {
                cnt = people[i];
                maxNo = i;
            }
        }
        System.out.println(no);
        System.out.print(maxNo);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
