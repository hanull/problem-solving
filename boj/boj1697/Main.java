package boj.boj1697;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int movingPoint, goalPoint;
    static int res = 0;
    static boolean[] visited;
    static final int MAX = 100000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        movingPoint = sc.nextInt();
        goalPoint = sc.nextInt();
        visited = new boolean[MAX + 1];

        bfs();
        System.out.println(res);
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(movingPoint, 0));
        visited[movingPoint] = true;
        while (!q.isEmpty()) {
            Node tmp = q.poll();
            int point = tmp.point;
            int time = tmp.time;

            if (point == goalPoint) {
                res = time;
                break;
            }

            if (isRange(point - 1) && !visited[point - 1]) {
                q.add(new Node(point - 1, time + 1));
                visited[point - 1] = true;
            }
            if (isRange(point + 1) && !visited[point + 1]) {
                q.add(new Node(point + 1, time + 1));
                visited[point + 1] = true;
            }
            if (isRange(point * 2) && !visited[point * 2]) {
                q.add(new Node(point * 2, time + 1));
                visited[point * 2] = true;
            }
        }

    }

    private static boolean isRange(int point) {
        return point < 0 || point > MAX ? false : true;
    }
}

class Node {
    int point, time;

    public Node(int point, int time) {
        this.point = point;
        this.time = time;
    }
}