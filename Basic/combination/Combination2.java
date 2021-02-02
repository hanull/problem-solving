package Basic.combination;

public class Combination2 {

    static int N, pick;
    static int[] arr;

    public static void main(String[] args) {
        N = 5;
        pick = 3;
        arr = new int[pick];
        combination(0, 1);
    }

    static void combination(int cnt, int start) {
        if (cnt == pick) {
            print();
            return;
        }

        for (int i = start; i <= N; i++) {
            arr[cnt] = i;
            combination(cnt + 1, i + 1);
        }
    }

    static void print() {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
