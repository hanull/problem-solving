package Basic.tree;

public class CompleteBinaryTreeTest {

    public static void main(String[] args) {
        CompleteBinaryTree tree = new CompleteBinaryTree(9);

        for (int i = 0; i < 9; i++) {
            tree.add((char) ('A' + i));
        }

       // tree.bfs();
        tree.dfs(1);

    }
}
