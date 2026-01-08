package problems.trees;

import javafx.util.Pair;
import java.util.Stack;

public class MaxDepthOfBinaryTree {

    /**
     * Calculates the maximum depth of a binary tree.
     * The depth of a binary tree is the number of nodes along the longest path from the root node down to the farthest leaf node.
     * The function processes each node in the tree, keeping track of the maximum depth encountered so far.
     * It uses a stack to keep track of nodes to be processed, and their respective depths.
     * The time complexity is O(n), where n is the number of nodes in the tree.
     * The space complexity is O(n), as the stack may store up to n nodes.
     * @param root the root node of the binary tree
     * @return the maximum depth of the binary tree
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 1));
        int maxDepth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> nodePair = stack.pop();
            TreeNode node = nodePair.getKey();
            int depth = nodePair.getValue();
            maxDepth = Math.max(depth, maxDepth);
            if (node.left != null) {
                stack.push(new Pair<>(node.left, depth + 1));
            }
            if (node.right != null) {
                stack.push(new Pair<>(node.right, depth + 1));
            }
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        MaxDepthOfBinaryTree maxDepthOfBinaryTree = new MaxDepthOfBinaryTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(maxDepthOfBinaryTree.maxDepth(root));
    }
}
