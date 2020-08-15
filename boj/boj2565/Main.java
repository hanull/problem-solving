package boj.boj2565;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int[] dp = new int[n];
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int key = sc.nextInt();
            int value = sc.nextInt();
            treeMap.put(key, value);
        }
        Iterator<Integer> iterator = treeMap.keySet().iterator();
        for (int i = 0; i < n; i++) {
            arr[i] = iterator.next();
        }
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (treeMap.get(arr[i]) > treeMap.get(arr[j])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        System.out.println(n - max);
    }
}
