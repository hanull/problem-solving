package boj.boj1244;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] switchStatus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        switchStatus = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            switchStatus[i] = stoi(st.nextToken());
        }

        int studentCount = stoi(br.readLine());
        for (int i = 0; i < studentCount; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = stoi(st.nextToken());
            int idx = stoi(st.nextToken());
            controlSwitch(gender, idx);
        }

        for (int i=1; i<=N; i++) {
            System.out.print(switchStatus[i] + " ");
            if (i % 20 == 0) {
                System.out.println();
            }
        }

    }

    static void controlSwitch(int gender, int idx) {
        if (gender == 1) {  // 남학생
            for (int i = idx; i <= N; i += idx) {
                switchStatus[i] = change(switchStatus[i]);
            }
        } else if ((idx == 1 || idx == N) || switchStatus[idx - 1] != switchStatus[idx + 1]) {
            switchStatus[idx] = change(switchStatus[idx]);
        } else {    // 여학생
            int left = idx - 1;
            int right = idx + 1;
            while (left > 0 && right <= N) {
                if (switchStatus[left] == switchStatus[right]) {
                    switchStatus[left] = change(switchStatus[left]);
                    switchStatus[right] = change(switchStatus[right]);
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            switchStatus[idx] = change(switchStatus[idx]);
        }
    }

    static int change(int idx) {
        return idx == 0 ? 1 : 0;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
