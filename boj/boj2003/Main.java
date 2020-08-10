package boj.boj2003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(arr, m));
    }

    private static int solution(int[] arr, int target) {
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int res = 0;
        while (true) {
            if (sum == target) res++;
            if (sum >= target) {
                sum -= arr[left];
                left++;
            } else if (right != arr.length-1) {
                right++;
                sum += arr[right];
            } else {
                break;
            }
        }
        return res;
    }
}
