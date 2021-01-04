package boj.boj3009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> pointX = new HashSet<>();
        HashSet<Integer> pointY = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            if (pointX.contains(x)) pointX.remove(x);
            else pointX.add(x);
            if (pointY.contains(y)) pointY.remove(y);
            else pointY.add(y);
        }
        for (int num : pointX) {
            System.out.print(num + " ");
        }
        for (int num : pointY) {
            System.out.println(num);
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
