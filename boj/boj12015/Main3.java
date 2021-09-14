package boj.boj12015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] number = new int[N];
        int[] d = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            number[i] = stoi(st.nextToken());
        }
        int len = 0;
        for (int i = 0; i < N; i++) {
            if (i == 0 || number[i] > d[len - 1]) {
                d[len] = number[i];
                len++;
            } else {
                int left = 0;
                int right = len;
                int index = 0;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (d[mid] < number[i]) {
                        left = mid + 1;
                    } else {
                        index = mid;
                        right = mid - 1;
                    }
                }
                d[index] = number[i];
            }
        }
        System.out.println(len);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
