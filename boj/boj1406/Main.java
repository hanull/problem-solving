package boj.boj1406;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String inputStr = br.readLine();
        LinkedList<String> list = new LinkedList<>(Arrays.asList(inputStr.split("")));
        ListIterator<String> listIterator = list.listIterator(list.size());
        int M = stoi(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "L":
                    if (listIterator.hasPrevious()) listIterator.previous();
                    break;
                case "D":
                    if (listIterator.hasNext()) listIterator.next();
                    break;
                case "B":
                    if (listIterator.hasPrevious()) {
                        listIterator.previous();
                        listIterator.remove();
                    }
                    break;
                case "P":
                    listIterator.add(st.nextToken());
                    break;
            }
        }
        for (String alpa : list) {
            bw.write(alpa);
        }
        bw.close();
        br.close();
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

