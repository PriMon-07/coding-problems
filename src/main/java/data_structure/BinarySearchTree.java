package data_structure;

public class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(Integer value) {
        this.root = insert(root, value);
    }

    private TreeNode insert(TreeNode treeNode, Integer value) {

        if (treeNode == null) {
            treeNode = new TreeNode(value);
            return treeNode;
        }

        if (value < treeNode.getValue()) {
            treeNode.setLeft(insert(treeNode.getLeft(), value));
        } else {
            treeNode.setRight(insert(treeNode.getRight(), value));
        }
        return treeNode;
    }

    public TreeNode search(Integer key) {
        return search(this.root, key);
    }

    private TreeNode search(TreeNode treeNode, Integer key) {
        if (treeNode == null) {
            return null;
        }
        if ((int) treeNode.getValue() == key) {
            return treeNode;
        }

        if (key < treeNode.getValue()) {
            return search(treeNode.getLeft(), key);
        } else {
            return search(treeNode.getRight(), key);
        }
    }

    public void delete(Integer value) {
        this.root =  delete(this.root, value);
    }

    private TreeNode delete(TreeNode treeNode, Integer value) {
        if (treeNode == null) {
            return null;
        }

        if (value < treeNode.getValue()) {
            treeNode.setLeft(delete(treeNode.getLeft(), value));
        } else if (value > treeNode.getValue()) {
            treeNode.setRight(delete(treeNode.getRight(), value));
        } else {
            if (treeNode.getLeft() == null) {
                return treeNode.getRight();
            } else if (treeNode.getRight() == null) {
                return treeNode.getLeft();
            }

            TreeNode successor = evaluateSuccessor(treeNode);
            treeNode.setValue(successor.getValue());
            treeNode.setRight(delete(treeNode.getRight(), successor.getValue()));
        }
        return treeNode;
    }

    private TreeNode evaluateSuccessor(TreeNode treeNode) {
        TreeNode suc = treeNode.getRight();
        while (suc.getLeft() != null) {
            suc = suc.getLeft();
        }
        return suc;
    }

    public void inOrder() {
       inOrder(this.root);
        System.out.println("\n");
    }

    private void inOrder(TreeNode treeNode) {
        if (treeNode != null) {
            inOrder(treeNode.getLeft());
            System.out.print(treeNode.getValue() + " ");
            inOrder(treeNode.getRight());
        }
    }

    public void preOrder() {
        preOrder(this.root);
        System.out.println("\n");
    }

    private void preOrder(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.print(treeNode.getValue() + " ");
            preOrder(treeNode.getLeft());
            preOrder(treeNode.getRight());
        }
    }

    public void postOrder() {
        postOrder(this.root);
        System.out.println("\n");
    }

    private void postOrder(TreeNode treeNode) {
        if (treeNode != null) {
            preOrder(treeNode.getLeft());
            preOrder(treeNode.getRight());
            System.out.print(treeNode.getValue() + " ");
        }
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(50);
        binarySearchTree.insert(30);
        binarySearchTree.insert(20);
        binarySearchTree.insert(40);
        binarySearchTree.insert(70);
        binarySearchTree.insert(60);
        binarySearchTree.insert(80);

        System.out.println("Inorder traversal: ");
        binarySearchTree.inOrder();

        binarySearchTree.delete(50);

        System.out.println("Preorder traversal: ");
        binarySearchTree.preOrder();

        System.out.println("Searching Node: " + binarySearchTree.search(30));

        System.out.println("Postorder traversal: ");
        binarySearchTree.postOrder();

    }
}
