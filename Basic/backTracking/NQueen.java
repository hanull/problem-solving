package Basic.backTracking;

import java.util.Scanner;

public class NQueen {

    static int answer, N;
    static int[] col;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        col = new int[N + 1];

        setQueen(0);
        System.out.println(answer);
    }

    static void setQueen(int rowNo) {
        if (!isAvailable(rowNo)) {
            return;
        }

        if (rowNo == N) {
            answer++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            col[rowNo + 1] = i; // 다음 열의 i열에 퀸을 놓는다.
            setQueen(rowNo + 1);

        }
    }

    static boolean isAvailable(int rowNo) {
        for (int i = 1; i < rowNo; i++) {
            // 퀸이 서로 위협적인지 체크
            // 같은 열인지, 대각선에 위치한지 체크(
            if (col[rowNo] == col[i] || Math.abs(col[rowNo] - col[i]) == rowNo - i) {
                return false;
            }
        }
        return true;
    }

}
