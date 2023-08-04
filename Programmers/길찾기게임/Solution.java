package Programmers.길찾기게임;

import java.util.*;

public class Solution {

    static class Node {
        int x, y, value;
        Node left;
        Node right;

        public Node(final int x, final int y, final int value) {
            this(x, y, value, null, null);
        }

        public Node(final int x, final int y, final int value, final Node left, final Node right) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        PriorityQueue<Node> nodes = new PriorityQueue<>((o1, o2) -> {
            if (o1.y == o2.y) {
                return o1.x - o2.x;
            }
            return o2.y - o1.y;
        });
        for (int i = 1; i <= nodeinfo.length; i++) {
            nodes.add(new Node(nodeinfo[i - 1][0], nodeinfo[i - 1][1], i));
        }

        Node root = nodes.poll();
        while (root != null && !nodes.isEmpty()) {
            makeTree(root, nodes.poll());
        }

        List<Integer> preOrderValues = new ArrayList<>();
        List<Integer> postOrderValues = new ArrayList<>();
        preOrder(preOrderValues, root);
        postOrder(postOrderValues, root);

        int[][] answer = new int[2][preOrderValues.size()];
        for (int i = 0; i < preOrderValues.size(); i++) {
            answer[0][i] = preOrderValues.get(i);
        }
        for (int i = 0; i < preOrderValues.size(); i++) {
            answer[1][i] = postOrderValues.get(i);
        }
        return answer;
    }

    private void makeTree(final Node root, final Node child) {
        if (root.x > child.x) {
            if (root.left == null) {
                root.left = child;
                return;
            }
            makeTree(root.left, child);
        } else {
            if (root.right == null) {
                root.right = child;
                return;
            }
            makeTree(root.right, child);
        }
    }

    private void preOrder(final List<Integer> values, final Node node) {
        if (node == null) {
            return;
        }
        values.add(node.value);
        preOrder(values, node.left);
        preOrder(values, node.right);
    }

    private void postOrder(final List<Integer> values, final Node node) {
        if (node == null) {
            return;
        }
        postOrder(values, node.left);
        postOrder(values, node.right);
        values.add(node.value);
    }
}
