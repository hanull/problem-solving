package boj.boj2629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
20
1 2 3 4 5 6 7 8 8 9 11 12 13 14 15 16 17 18 18 19
7
1 2 3 4 5 10 20
 */

public class Main {

    static int[] boxWeight;
    static int boxCount;
    static boolean[][] visited;
    static HashSet<Integer> possibleWeight = new HashSet<>(); 	// 추를 통해서 만들 수 있는 모든 무게를 저장하는 hashset

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boxCount = stoi(br.readLine());
        boxWeight = new int[boxCount];
        visited = new boolean[boxCount + 1][55000];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalWeight = 0;
        for (int i=0; i<boxCount; i++) {	// 추의 무게를 저장
            boxWeight[i] = stoi(st.nextToken());
            possibleWeight.add(boxWeight[i]);
            totalWeight += boxWeight[i];
        }
        possibleWeight.add(totalWeight);

        int ballCount = stoi(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] ballWeight = new int[ballCount];
        for (int i=0; i<ballCount; i++) {	// 구슬의 무게를 저장
            ballWeight[i] = stoi(st.nextToken());
        }

        findAllBallWeight(0, 0, totalWeight);	// 추의 개수 만큼 dfs 탐색을 진행. 모든 무게를 찾는다

        StringBuilder result = new StringBuilder();
        for (int i=0; i<ballCount; i++) {	// 해당 구슬의 무게가 사용할 수 있는 무게일 경우 Y를 , 사용할 수 없는 경우 N을 출력한다
            if (possibleWeight.contains(ballWeight[i])) result.append("Y ");
            else result.append("N ");
        }
        System.out.print(result);

    }

    static void findAllBallWeight(int index, int total, int remain) {	 // 모든 추를 활용하여 구할 수 있는 모든 무게를 구한다.
        if (total + remain < 1) return;	// 남아 있는 추의 무게를 모두 더해도 1보다 작을 경우, 만들 수 없는 무게 이므로 return 해준다
        if (index == boxCount) {		// 모든 추를 탐색 한 경우, 해당 무게를 hashset에 추가해준다
            possibleWeight.add(total);
            return;
        }

        if (visited[index][total]) return;
        visited[index][total] = true;
        findAllBallWeight(index + 1, total, remain);	// 해당 추를 저울에 올려 놓지 않은 경우
        findAllBallWeight(index + 1, total + boxWeight[index], remain - boxWeight[index]);	// 해당 추를 구슬의 반대편에 올려놓을 경우
        findAllBallWeight(index + 1, Math.abs(total - boxWeight[index]), remain - boxWeight[index]);	// 해당 추를 구슬과 같은 저울에 올려 놓을 경우
    }

    static int stoi(String input) {	// 입력 데이터를 int형으로 변환한다
        return Integer.parseInt(input);
    }

}
