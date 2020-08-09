package boj.boj1072;

import java.util.Scanner;

public class Main {

    private static int binarySearch(long x, long y) {
        int res = 0;
        int left = 0;
        int right = (int)x;
        if (!isPossible(x,y)) return -1;
        int target = (int)(y * 100 / x);
        while (left <= right) {
            int mid = (left + right) / 2;
            if (getAverage(x, y, mid, target)) {
                right = mid - 1;
                res = mid;
            }else {
                left = mid + 1;
            }
        }
        return res;
    }

    private static boolean isPossible(long x, long y) {
        long tmpX = x;
        long tmpY = y * 100;
        return (int)(tmpY / tmpX) >= 99 ? false : true;
    }

    private static boolean getAverage(long x, long y, int mid, int target) {
        long tmpX = x + mid;
        long tmpY = y + mid;
        int av = (int)((tmpY * 100) / tmpX);
        if (av > target)
            return true;
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        long y = sc.nextLong();
        System.out.println(binarySearch(x, y));
    }
}
