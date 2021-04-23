package Test.모의검정.삼국지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[][] country;
    static int[][] militaryPower;
    static int[][] plusPower;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Node> targetList = new ArrayList<>();
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        int T = stoi(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            init();
            int answer;
            while (true) {
                if (isUnifyTheWord()) {
                    answer = calculate();
                    break;
                }
                for (int countryNo = 1; countryNo <= 3; countryNo++) {
                    if (!checkCountry(countryNo)) continue;
                    // 공격
                    attack(countryNo);
                    // 지원
                    help(countryNo);
                    // 전지역 보충
                    plusAllOfMap();
                }
            }
            result.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(result);
    }

    private static boolean checkCountry(int countryNo) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (country[i][j] == countryNo) return true;
            }
        }
        return false;
    }

    private static int calculate() {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += militaryPower[i][j];
            }
        }
        return answer;
    }

    private static boolean isUnifyTheWord() {
        boolean[] flag = new boolean[3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (country[i][j] == 1 && !flag[0]) flag[0] = true;
                else if (country[i][j] == 2 && !flag[1]) flag[1] = true;
                else if (country[i][j] == 3 && !flag[2]) flag[2] = true;
            }
        }
        int count = 0;
        for (boolean f : flag) {
            if (f) count++;
        }
        return count == 1;
    }

    private static void plusAllOfMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                militaryPower[i][j] += plusPower[i][j];
            }
        }
    }

    private static void help(int countryNo) {
        int[][] plusMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int helpCount = militaryPower[i][j] / 5;
                if (country[i][j] == countryNo) {
                    int enemyCount = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (!isRange(nx, ny)) continue;
                        if (country[nx][ny] == 0) continue;
                        if (country[nx][ny] != countryNo) enemyCount++;
                    }
                    if (enemyCount == 0) {
                        for (int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if (!isRange(nx, ny)) continue;
                            if (country[nx][ny] == 0) continue;
                            plusMap[nx][ny] += helpCount;
                            plusMap[i][j] -= helpCount;
                        }
                    } else {
                        for (int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if (!isRange(nx, ny)) continue;
                            if (country[nx][ny] != countryNo) continue;
                            if (country[nx][ny] == 0) continue;
                            if (militaryPower[i][j] <= militaryPower[nx][ny] * 5) continue; // 5배 초과하는지 확인
                            plusMap[nx][ny] += helpCount;
                            plusMap[i][j] -= helpCount;
                        }
                    }
                }
            }
        }
        // 지원한 병력만큼 갱신
        for (int p = 0; p < N; p++) {
            for (int q = 0; q < N; q++) {
                militaryPower[p][q] += plusMap[p][q];
            }
        }
    }

    private static void attack(int countryNo) {
        int[][] minusMap = new int[N][N];
        for (int i = 0; i < N; i++) {   // 점령 가능한 나라 체크
            for (int j = 0; j < N; j++) {
                if (country[i][j] != 0 && country[i][j] != countryNo && isPossibleToAttack(i, j, militaryPower[i][j], countryNo)) { // 점령 가능한 나라이면,
//                    country[i][j] = countryNo;
                    militaryPower[i][j] *= -1;
                    targetList.add(new Node(i, j));
                    minusMap[i][j] *= -1;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (!isRange(nx, ny)) continue;
                        if (country[nx][ny] == countryNo) {
                            minusMap[nx][ny] -= militaryPower[nx][ny] / 4;    // 1/4 만큼 병력 마이너스
                            minusMap[i][j] += militaryPower[nx][ny] / 4;
                        }
                    }
                }
            }
        }
        for (Node node : targetList) {
            country[node.x][node.y] = countryNo;
        }
        targetList.clear();
        // 공격한 병력만큼 빼준다.
         for (int i = 0; i < N; i++) {
             for (int j = 0; j < N; j++) {
                 militaryPower[i][j] += minusMap[i][j];
             }
         }
    }

    private static boolean isPossibleToAttack(int i, int j, int enemyPower, int countryNo) {
        int power = 0;
        for (int d = 0; d < 4; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];
            if (!isRange(nx, ny)) continue;
            if (country[nx][ny] == countryNo) {
                power += militaryPower[nx][ny];
            }
        }
        return power > enemyPower * 5;
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
    }

    private static void init() throws IOException {
        N = stoi(br.readLine());
        country = new int[N][N];
        militaryPower = new int[N][N];
        plusPower = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                country[i][j] = stoi(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                militaryPower[i][j] = stoi(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                plusPower[i][j] = stoi(st.nextToken());
            }
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
