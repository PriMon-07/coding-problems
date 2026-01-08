package problems.trees;

import java.util.Stack;


/**
 * Problem Statement:
 * Given a binary tree, invert the tree without changing the original tree.
 *
 * Example 1:
 * Input: root = [4, 2, 7, 1, 3, 6, 9]
 * Output: [4, 7, 2, 9, 6, 3, 1]
 *
 * Example 2:
 * Input: root = [2, 1, 3]
 * Output: [2, 3, 1]
 */
public class InvertBinaryTree {

    /**
     * This implementation uses a stack to traverse the tree using depth-first search.
     * The time complexity is O(n), where n is the number of nodes in the tree.
     * The space complexity is O(n), as the stack may store up to n nodes.
     */
    public TreeNode invertTreeDFS(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        InvertBinaryTree invertBinaryTree = new InvertBinaryTree();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        System.out.println(invertBinaryTree.invertTreeDFS(root));
    }
}
