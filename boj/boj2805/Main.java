package boj.boj2805;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] tree = new int[n];
        int max = 0;
        for (int i=0; i<n; i++) {
            tree[i] = sc.nextInt();
            if (max < tree[i]) max = tree[i];
        }
        Arrays.sort(tree);
        System.out.println(binarySearch(tree, 0, max, m));
    }

    private static int binarySearch(int[] tree, int start, int end, int target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (sliceTree(tree, mid, target)) {
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return end;
    }

    private static boolean sliceTree(int[] tree, int height, int target) {
        long total = 0;
        for (int i=0; i<tree.length; i++) {
            total += tree[i] - height > 0 ? tree[i] - height : 0;
        }
        if (total >= target)
            return true;
        return false;
    }
}
