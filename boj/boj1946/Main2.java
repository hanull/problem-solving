package boj.boj1946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {

    static class Interviewer {
        int documentScore, interviewScore;

        public Interviewer(final int documentScore, final int interviewScore) {
            this.documentScore = documentScore;
            this.interviewScore = interviewScore;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            List<Interviewer> interviewers = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int documentScore = Integer.parseInt(st.nextToken());
                int interviewScore = Integer.parseInt(st.nextToken());
                interviewers.add(new Interviewer(documentScore, interviewScore));
            }
            interviewers.sort(Comparator.comparingInt(o -> o.documentScore));
            int count = 1;
            int index = 0;
            for (int i = 1; i < N; i++) {
                final Interviewer currentInterviewer = interviewers.get(index);
                final Interviewer otherInterviewer = interviewers.get(i);
                if (currentInterviewer.interviewScore > otherInterviewer.interviewScore) {
                    count++;
                    index = i;
                }
            }
            System.out.println(count);
        }
    }
}
