package data_structure;

import lombok.Data;

@Data
public class TreeNode {
    private Integer value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Integer value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}