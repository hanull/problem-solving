package boj.boj2304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static int N;
    static int[] arr = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[stoi(st.nextToken())] = stoi(st.nextToken());
        }

        int mid = 0;
        int height = 0;
        for (int i = 1; i <= 1000; i++) {
            if (arr[i] > height) {
                height = arr[i];
                mid = i;
            }
        }

        int totalArea = 0;

        int left = 1;
        height = arr[left];
        while (left <= mid) {
            if (arr[left] > height) {
                height = arr[left];
            }
            left++;
            totalArea += height;
        }

        int right = 1000;
        height = arr[right];
        while (right > mid) {
            if (arr[right] > height) {
                height = arr[right];
            }
            right--;
            totalArea += height;
        }

        System.out.println(totalArea);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
