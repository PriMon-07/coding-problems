package data_structure.binary_tree;

public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(Integer value) {
        this.root = insert(root, value);
    }

    private Node insert(Node node, Integer value) {

        if (node == null) {
            node = new Node(value);
            return node;
        }

        if (value < node.getValue()) {
            node.setLeft(insert(node.getLeft(), value));
        } else {
            node.setRight(insert(node.getRight(), value));
        }
        return node;
    }

    public Node search(Integer key) {
        return search(this.root, key);
    }

    private Node search(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        if ((int) node.getValue() == key) {
            return node;
        }

        if (key < node.getValue()) {
            return search(node.getLeft(), key);
        } else {
            return search(node.getRight(), key);
        }
    }

    public void delete(Integer value) {
        this.root =  delete(this.root, value);
    }

    private Node delete(Node node, Integer value) {
        if (node == null) {
            return null;
        }

        if (value < node.getValue()) {
            node.setLeft(delete(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(delete(node.getRight(), value));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            Node successor = evaluateSuccessor(node);
            node.setValue(successor.getValue());
            node.setRight(delete(node.getRight(), successor.getValue()));
        }
        return node;
    }

    private Node evaluateSuccessor(Node node) {
        Node suc = node.getRight();
        while (suc.getLeft() != null) {
            suc = suc.getLeft();
        }
        return suc;
    }

    public void inOrder() {
       inOrder(this.root);
        System.out.println("\n");
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getValue() + " ");
            inOrder(node.getRight());
        }
    }

    public void preOrder() {
        preOrder(this.root);
        System.out.println("\n");
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public void postOrder() {
        postOrder(this.root);
        System.out.println("\n");
    }

    private void postOrder(Node node) {
        if (node != null) {
            preOrder(node.getLeft());
            preOrder(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }
}
