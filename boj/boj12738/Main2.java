package boj.boj12738;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] temp = new int[N];
        int len = 0;
        for (int i = 0; i < N; i++) {
            if (i == 0 || temp[len - 1] < arr[i]) {
                temp[len++] = arr[i];
            } else {
                int left = 0;
                int right = len - 1;
                int index = 0;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (temp[mid] < arr[i]) {
                        left = mid + 1;
                    } else {
                        index = mid;
                        right = mid - 1;
                    }
                }
                temp[index++] = arr[i];
            }
        }
        System.out.println(len);
    }
}
