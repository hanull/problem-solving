package Programmers.표현가능한이진트리;

import java.util.*;

public class Solution {

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int index = 0;
        for (long number : numbers) {
            String binaryTree = makePerfectBinaryTree(Long.toBinaryString(number));
            answer[index] = isPerfectBinaryTree(binaryTree) ? 1 : 0;
            index++;
        }
        return answer;
    }

    private boolean isPerfectBinaryTree(String binaryTree) {
        if (binaryTree.length() == 1) {
            return true;
        }
        int mid = binaryTree.length() / 2;
        String left = binaryTree.substring(0, mid);
        String right = binaryTree.substring(mid + 1);

        if (isPerfectBinaryTree(left) && isPerfectBinaryTree(right)) {
            if (binaryTree.charAt(mid) == '1') {
                return true;
            } else {    // 루트 노드가 0인 경우, 왼쪽과 오른쪽의 자식 노드 모두 0이여야 한다.
                return left.charAt(left.length() / 2) == '0' && right.charAt(right.length() / 2) == '0';
            }
        }
        return false;
    }

    private String makePerfectBinaryTree(String binary) {
        List<Integer> perfectBinaryTreeCounts = List.of(1, 3, 7, 15, 31, 63);
        int nodeCount = binary.length();
        if (perfectBinaryTreeCounts.contains(nodeCount)) {
            return binary;
        }

        StringBuilder binaryBuilder = new StringBuilder(binary);
        for (int i = perfectBinaryTreeCounts.size() - 1; i >= 0; i--) {
            if (nodeCount > perfectBinaryTreeCounts.get(i)) {
                int addCount = perfectBinaryTreeCounts.get(i + 1) - nodeCount;
                while (addCount-- > 0) {
                    binaryBuilder.insert(0, "0");
                }
                break;
            }
        }
        return binaryBuilder.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(new long[]{7, 42, 5})));
        System.out.println(Arrays.toString(sol.solution(new long[]{63, 111, 95})));
    }
}
