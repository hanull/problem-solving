package boj.boj1946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Applicant[] applicants;
    static class Applicant implements Comparable<Applicant>{
        int documentScore, interviewScore;

        public Applicant(int documentScore, int interviewScore) {
            this.documentScore = documentScore;
            this.interviewScore = interviewScore;
        }

        @Override
        public int compareTo(Applicant o) {
            return Integer.compare(this.documentScore, o.documentScore);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        while (T-- > 0) {
            N = stoi(br.readLine());
            applicants = new Applicant[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                applicants[i] = new Applicant(stoi(st.nextToken()), stoi(st.nextToken()));
            }
            Arrays.sort(applicants);
            int cnt = 1;
            int idx = 0;
            for (int i = 1; i < N; i++) {
                if (applicants[i].interviewScore < applicants[idx].interviewScore) {
                    cnt++;
                    idx = i;
                }
            }
            System.out.println(cnt);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
