package boj.boj1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Time implements Comparable<Time>{
    int start;
    int end;

    public Time(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Time o) {
        if (this.end == o.end) {
            return Integer.compare(this.start, o.start);
        }
        return Integer.compare(this.end, o.end);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Time> list = new ArrayList<Time>();

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            int start = Integer.parseInt(str[0]);
            int end = Integer.parseInt(str[1]);
            list.add(new Time(start, end));
        }
        Collections.sort(list);
        int res = 1;
        int end = list.get(0).end;
        for (int i = 1; i < n; i++) {
            int start = list.get(i).start;
            if (start >= end) {
                res++;
                end = list.get(i).end;
            }
        }
        System.out.println(res);
    }
}
