package data_structure;

public class LinkedList {
    private Node head;

    public void insert(int value) {
        Node node = new Node(value);
        if (this.head == null) {
            head = node;
            return;
        }

        Node current = this.head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(node);
    }

    public boolean delete(int value) {
        if (head.getValue() == value) {
            head = head.getNext();
            return true;
        }

        Node prev;
        Node curr;
        prev = head;
        curr = head.getNext();
        while (curr != null && curr.getValue() != value) {
            prev = curr;
            curr = curr.getNext();
        }

        if (curr != null && curr.getValue() == value) {
            prev.setNext(curr.getNext());
            return true;
        } else {
            return false;
        }
    }

    public void printLinkedList() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.getValue() + " -> ");
            curr = curr.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insert(1);
        linkedList.insert(2);
        linkedList.insert(3);
        linkedList.insert(4);

        linkedList.printLinkedList();

        linkedList.delete(2);

        linkedList.printLinkedList();

        linkedList.delete(1);

        linkedList.printLinkedList();

        linkedList.delete(4);

        linkedList.printLinkedList();

        linkedList.delete(1);
    }
}
