package data_structure;

import lombok.Data;

@Data
public class Node {
    private int value;
    private Node previous;
    private Node next;

    public Node (int value) {
        this.value = value;
        this.previous = null;
        this.next = null;
    }
}
