package boj.boj16935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, max;
    static int[][] map, tmp;
    static char[] opArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        max = Math.max(N, M);
        map = new int[max][max];
        tmp = new int[max][max];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        opArr = br.readLine().toCharArray();
        for (int i = 0; i < opArr.length; i++) {
            copyOfArray();
            solve(opArr[i] - '0');
        }
        print();
    }

    static void copyOfArray() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = map[i][j];
            }
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void solve(int opNum) {
        int midX = N / 2;
        int midY = M / 2;
        switch (opNum) {
            case 1: //  상하 반전
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        map[i][j] = tmp[N - 1 - i][j];
                    }
                }
                break;
            case 2: // 좌우 반전
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        map[i][j] = tmp[i][M - 1 - j];
                    }
                }
                break;
            case 3: // 오른쪽으로 90도
                for (int i = 0; i < M; i++) {
                    for (int j = 0; j < N; j++) {
                        map[i][j] = tmp[N - 1 - j][i];
                    }
                }
                int a = N;
                N = M;
                M = a;
                break;
            case 4: // 왼쪽으로 90도
                for (int i = 0; i < M; i++) {
                    for (int j = 0; j < N; j++) {
                        map[i][j] = tmp[j][M - 1 - i];
                    }
                }
                a = N;
                N = M;
                M = a;
                break;
            case 5: // 그룹 이동, 1->2, 2->3, 3->4, 4->1
                for (int i = 0; i <midX; i++) {
                    for (int j = 0; j < midY; j++) {
                        map[i][j] = tmp[i + midX][j];
                    }
                }
                for (int i = 0; i < midX; i++) {
                    for (int j = midY; j < M; j++) {
                        map[i][j] = tmp[i][j - midY];
                    }
                }
                for (int i = midX; i < N; i++) {
                    for (int j = 0; j < midY; j++) {
                        map[i][j] = tmp[i][j + midY];
                    }
                }
                for (int i = midX; i < N; i++) {
                    for (int j = midY; j < M; j++) {
                        map[i][j] = tmp[i - midX][j];
                    }
                }
                break;
            case 6: // 그룹 이동, 1->4, 4->3, 3->2, 2->1
                for (int i = 0; i <midX; i++) {
                    for (int j = 0; j < midY; j++) {
                        map[i][j] = tmp[i][j+midY];
                    }
                }
                for (int i = 0; i < midX; i++) {
                    for (int j = midY; j < M; j++) {
                        map[i][j] = tmp[i + midX][j];
                    }
                }
                for (int i = midX; i < N; i++) {
                    for (int j = 0; j < midY; j++) {
                        map[i][j] = tmp[i - midX][j];
                    }
                }
                for (int i = midX; i < N; i++) {
                    for (int j = midY; j < M; j++) {
                        map[i][j] = tmp[i][j - midY];
                    }
                }
                break;
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
