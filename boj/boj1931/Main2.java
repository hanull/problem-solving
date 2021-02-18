package boj.boj1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

    static int N;
    static Meeting[] meetings;
    static class Meeting implements Comparable<Meeting>{
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.end == o.end) {
                return Integer.compare(this.start, o.start);
            }
            return Integer.compare(this.end, o.end);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i] = new Meeting(stoi(st.nextToken()), stoi(st.nextToken()));
        }
        Arrays.sort(meetings);
        int cnt = 1;
        int idx = 0;
        for (int i = 1; i < N; i++) {
            if (meetings[i].start >= meetings[idx].end) {
                cnt++;
                idx = i;
            }
        }
        System.out.println(cnt);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
