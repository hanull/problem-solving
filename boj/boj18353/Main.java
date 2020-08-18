package boj.boj18353;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        ArrayList<Integer> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        list.add(arr[0]);
        for (int i = 1; i < n; i++) {
            if (list.get(list.size() - 1) <= arr[i]) {
                list.set(lower_bound(list, arr[i]), arr[i]);
            } else {
                list.add(arr[i]);
            }
        }
        System.out.println(n - list.size());
    }

    private static int lower_bound(ArrayList<Integer> list, int num) {
        int left = 0;
        int right = list.size() - 1;
        int res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= num) {
                right = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
