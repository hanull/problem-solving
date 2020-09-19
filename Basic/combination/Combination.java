package Basic.combination;

/**
 * 조합(Combination)
 * n개의 배열 중 r개 뽑기
 */
public class Combination {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int len = arr.length;
        boolean[] visited = new boolean[len];
        int r = 2;

        System.out.println("Backtracking---------");
        comb_backtracking(arr, visited, len, 0, 0, r);
        System.out.println("---------------------");
        visited = new boolean[len];
        System.out.println("Recursive-------------");
        comb_recursive(arr, visited, len, 0, 0, r);
        System.out.println("---------------------");
    }

    private static void comb_backtracking(int[] arr, boolean[] visited, int len, int start, int cnt, int r) {
        if (cnt == r) {
            printArr(arr, visited);
            return;
        }
        for (int i = start; i < len; i++) {
            visited[i] = true;
            comb_backtracking(arr, visited, len,i + 1, cnt + 1, r);
            visited[i] = false;
        }
    }

    private static void comb_recursive(int[] arr, boolean[] visited, int depth, int idx, int cnt, int r) {
        if (cnt == r) {
            printArr(arr, visited);
            return;
        }
        if (depth == idx) {
            return;
        }
        visited[idx] = true;
        comb_recursive(arr, visited, depth, idx + 1, cnt + 1, r);
        visited[idx] = false;
        comb_recursive(arr, visited, depth, idx + 1, cnt, r);
    }

    private static void printArr(int[] arr, boolean[] visited) {
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
}
