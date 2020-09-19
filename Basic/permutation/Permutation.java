package Basic.permutation;

public class Permutation {

    public static void main(String[] args) {
        int len = 4;
        int[] arr = {1, 2, 3, 4};
        boolean[] visited = new boolean[len];
        int r = 4;
        int[] output = new int[r];
        int cnt = 0;

        permutation(arr, visited, output, len, cnt, r);
    }

    private static void permutation(int[] arr, boolean[] visited, int[] output, int len, int cnt, int r) {
        if (cnt == r) {
            printArr(output, r);
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[cnt] = arr[i];
                permutation(arr, visited, output, len, cnt + 1, r);
                visited[i] = false;
            }
        }
    }

    private static void printArr(int[] arr, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
