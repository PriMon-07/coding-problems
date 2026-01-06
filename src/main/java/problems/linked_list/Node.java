package problems.linked_list;

public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public Node(int val, Node next, Node random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", next=" + (next != null ? next.val : "null") +
                ", random=" + (random != null ? random.val : "null") +
                '}';
    }
}
