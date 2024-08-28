package data_structure;

import lombok.Data;

@Data
public class Node {
    private Integer value;
    private Node left;
    private Node right;

    public Node(Integer value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}