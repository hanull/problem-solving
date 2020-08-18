package boj.boj10989;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[10001];
        int max = 0;
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(br.readLine());
            arr[tmp] += 1;
            if (tmp > max) max = tmp;
        }
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= max; i++) {
            int loop = arr[i];
            for (int j = 0; j < loop; j++) {
                wr.write(Integer.toString(i) + '\n');
            }
        }
        br.close();
        wr.close();
    }
}
