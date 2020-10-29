package boj.boj14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int H, W;
    static int[] arr;
    static int max, mid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = stoi(st.nextToken());
        W = stoi(st.nextToken());
        arr = new int[W];
        st = new StringTokenizer(br.readLine());
        max = 0;
        for (int i = 0; i < W; i++) {
            arr[i] = stoi(st.nextToken());
            if (arr[i] >= max) {
                max = arr[i];
                mid = i;
            }
        }
        System.out.println(solve());
    }

    private static int solve() {
        int res = 0;
        int left = 0;
        int tmp = arr[0];
        while (left < mid) {
            if (arr[left] >= tmp) {
                tmp = arr[left];
            } else {
                res += (tmp - arr[left]);
            }
            left++;
        }
        int right = W - 1;
        tmp = arr[W - 1];
        while (right > mid) {
            if (arr[right] >= tmp) {
                tmp = arr[right];
            } else {
                res += (tmp - arr[right]);
            }
            right--;
        }
        return res;
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
