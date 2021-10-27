package boj.boj14003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] temp = new int[N];
        int[] point = new int[N];
        int len = 0;
        for (int i = 0; i < N; i++) {
            if (i == 0 || temp[len - 1] < arr[i]) {
                point[i] = len;
                temp[len++] = arr[i];
            } else {
                int left = 0;
                int right = len - 1;
                int index = 0;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (temp[mid] < arr[i]) {
                        left = mid + 1;
                    } else {
                        index = mid;
                        right = mid - 1;
                    }
                }
                point[i] = index;
                temp[index++] = arr[i];
            }
        }
        System.out.println(len);
        List<Integer> list = new ArrayList<>();
        int index = len - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (point[i] != index) continue;
            list.add(arr[i]);
            if (index == 0) break;
            index--;
        }
        StringBuilder answer = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            answer.append(list.get(i));
            if (i > 0) answer.append(" ");
        }
        System.out.print(answer);
    }
}
