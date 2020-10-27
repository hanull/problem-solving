package boj.boj2304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr = new int[1001];
    static int total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            arr[x] = y;
        }
        int mid = 0;
        int max = 0;
        for (int i = 0; i <= 1000; i++) {
            if (max < arr[i]) {
                max = arr[i];
                mid = i;
            }
        }
        int left = 0;
        int plus = arr[left];
        while (left < mid) {
            if (arr[left] > plus) {
               plus = arr[left];
            }
            total += plus;
            left++;
        }
        int right = 1000;
        plus = arr[right];
        while (right > mid) {
            if (arr[right] > plus) {
                plus = arr[right];
            }
            total += plus;
            right--;
        }
        total += arr[mid];
        System.out.println(total);
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}