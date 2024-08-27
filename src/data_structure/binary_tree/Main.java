package data_structure.binary_tree;

public class Main {
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
