package boj.boj1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        tree = new Node[N];

        for (int i = 0; i < N; i++) {
            tree[i] = new Node((char) (i + 'A'), null, null);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char data = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            if (left != '.') tree[data - 'A'].left = tree[left - 'A'];
            if (right != '.') tree[data - 'A'].right = tree[right - 'A'];
        }

        preOrder(tree[0]);
        System.out.println();
        inOrder(tree[0]);
        System.out.println();
        postOrder(tree[0]);
    }

    private static void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data);
    }

    private static void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data);
        inOrder(node.right);
    }

    private static void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
class Node {
    char data;
    Node left, right;

    public Node(char data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}