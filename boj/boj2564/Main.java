package boj.boj2564;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static class Node {
        int x, y, d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        int shopCount = stoi(br.readLine());
        Node[] shop = new Node[shopCount];
        for (int i = 0; i < shopCount; i++) {
            st = new StringTokenizer(br.readLine());
            int d = stoi(st.nextToken());
            int dist = stoi(st.nextToken());
            shop[i] = getPoint(d, dist);
        }
        st = new StringTokenizer(br.readLine());
        Node startPoint = getPoint(stoi(st.nextToken()), stoi(st.nextToken()));

        int oppositeSide = findOppositeSide(startPoint.d);
        int totalDistance = 0;
        for (Node target : shop) {
            if (oppositeSide == target.d) {
                if (target.d == 1 || target.d == 2) {
                    totalDistance += Math.min((startPoint.y + startPoint.x + target.x + target.y), (M - startPoint.y + M - target.y + startPoint.x + target.x));
                } else {
                    totalDistance += Math.min(N - target.x + N - startPoint.x + target.y + startPoint.y, startPoint.y + startPoint.x + target.x + target.y);
                }
            } else {
                totalDistance += Math.abs(startPoint.x - target.x) + Math.abs(startPoint.y - target.y);
            }
        }
        System.out.println(totalDistance);
    }

    static int findOppositeSide(int d) {
        if (d == 1) {
            return 2;
        } else if (d == 2) {
            return 1;
        } else if (d == 3) {
            return 4;
        }
        return 3;
    }

    static Node getPoint(int d, int dist) {
        Node tmp = null;
        if (d == 1) {
            tmp = new Node(0, dist, d);
        } else if (d == 2) {
            tmp = new Node(N, dist, d);
        } else if (d == 3) {
            tmp = new Node(dist, 0, d);
        } else {
            tmp = new Node(dist, M, d);
        }
        return tmp;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
