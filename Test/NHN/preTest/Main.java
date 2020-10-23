package Test.NHN.preTest;

import java.util.*;

class Main {

    static int[] dx = {0,0,1,-1};
    static int[] dy = {-1,1,0,0};

    private static void solution(int sizeOfMatrix, int[][] matrix) {
        boolean[][] visited = new boolean[sizeOfMatrix][sizeOfMatrix];
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = 0; j < sizeOfMatrix; j++) {
                if (!visited[i][j] && matrix[i][j] == 1) {
                    int size = bfs(i, j, matrix, visited, sizeOfMatrix);
                    res.add(size);
                }
            }
        }
        Collections.sort(res);
        System.out.println(res.size());
        if (res.size() == 0) return;
        for (int size : res) {
            System.out.print(size + " ");
        }
    }

    private static int bfs(int x, int y, int[][] matrix, boolean[][] visited, int n) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        visited[x][y] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            Pair tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[nx][ny] || matrix[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                q.add(new Pair(nx, ny));
                cnt++;
            }
        }
        return cnt;
    }

    private static class InputData {
        int sizeOfMatrix;
        int[][] matrix;
    }

    private static InputData processStdin() {
        InputData inputData = new InputData();

        try (Scanner scanner = new Scanner(System.in)) {
            inputData.sizeOfMatrix = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

            inputData.matrix = new int[inputData.sizeOfMatrix][inputData.sizeOfMatrix];
            for (int i = 0; i < inputData.sizeOfMatrix; i++) {
                String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
                for (int j = 0; j < inputData.sizeOfMatrix; j++) {
                    inputData.matrix[i][j] = Integer.parseInt(buf[j]);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return inputData;
    }

    public static void main(String[] args) throws Exception {
        InputData inputData = processStdin();

        solution(inputData.sizeOfMatrix, inputData.matrix);
    }
}

class Pair {
    int x,y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}