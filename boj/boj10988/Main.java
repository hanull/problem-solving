package boj.boj10988;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] arr = str.toCharArray();
        int left = 0;
        int right = arr.length - 1;
        boolean flag = false;
        while (left < right) {
            if (arr[left] == arr[right]) {
                left++;
                right--;
            } else {
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println(0);
        } else {
            System.out.println(1);
        }
    }
}
