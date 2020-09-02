package boj.boj2660;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n + 1][n + 1];
        int[] grade = new int[n + 1];
        int[] answer = new int[n + 1];
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {   // 초기화
            for (int j = 1; j <= n; j++) {
                arr[i][j] = (i == j) ? 0 : 50;
            }
        }
        while (true) {  // 정점 연결
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (x == -1 && y == -1) {
                break;
            }
            arr[x][y] = 1;
            arr[y][x] = 1;
        }
        for (int k = 1; k <= n; k++) {  // 플로이드와샬, 모든 정점 사이의 최단거리 구하기
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] > 0 && arr[i][j] < 50 && arr[i][j] > grade[i]) {
                    grade[i] = arr[i][j];
                }
            }
            if (min > grade[i]) {
                index = 0;
                min = grade[i];
                answer[index] = i;
                index++;
            } else if (min == grade[i]){
                answer[index] = i;
                index++;
            }
        }
        sb.append(min + " " + index + "\n");
        for (int i = 0; i < index; i++) {
            sb.append(answer[i] + " ");
        }
        System.out.println(sb.toString());
    }

}
