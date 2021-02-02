package Basic.permutation;

public class Permutation2 {

    static int N = 3;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) {
        arr = new int[N];
        visited = new boolean[N + 1];
        permutation(0);
    }

    static void permutation(int cnt) {
        if (cnt == N) {
            print();
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            arr[cnt] = i;
            permutation(cnt + 1);
            visited[i] = false;
        }
    }

    static void print() {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
