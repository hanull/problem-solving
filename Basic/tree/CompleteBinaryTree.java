package Basic.tree;

import java.util.LinkedList;
import java.util.Queue;

public class CompleteBinaryTree {

    private char[] nodes;
    private int lastIndex;
    private final int SIZE;

    public CompleteBinaryTree(int size) {
        this.SIZE = size;
        nodes = new char[size + 1];
    }

    public boolean isEmpty() {
        return lastIndex == 0;
    }

    public boolean isFull() {
        return lastIndex == SIZE;
    }

    public void add(char e) {
        if (isFull()) return;
        nodes[++lastIndex] = e;
    }

    public void bfs() {
        if (isEmpty()) return;

        // 탐색 순서 번호를 큐로 관리
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print("level" + level + " : ");
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                System.out.print(nodes[current] + " ");
                if (current * 2 <= lastIndex) {
                    queue.offer(current * 2);
                }
                if (current * 2 + 1 <= lastIndex) {
                    queue.offer(current * 2 + 1);
                }
            }
            ++level;
            System.out.println();
        }
    }

    // 전위 순회
    public void dfs(int current) {
        if (current > lastIndex) return;

        System.out.println(nodes[current]);
        dfs(current * 2);
        dfs(current * 2 + 1);
    }

}
