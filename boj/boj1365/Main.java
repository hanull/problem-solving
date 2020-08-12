package boj.boj1365;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(arr));
    }

    private static int solution(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (list.isEmpty()) {
                list.add(arr[i]);
            } else if (list.get(list.size() - 1) < arr[i]) {
                list.add(arr[i]);
            } else {
                list.set(lower_bound(list, arr[i]), arr[i]);
            }
        }
        return arr.length - list.size();
    }

    private static int lower_bound(ArrayList<Integer> list, int val) {
        int left = 0;
        int right = list.size() - 1;
        int res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= val) {
                right = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
