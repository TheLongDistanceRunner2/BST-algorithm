// https://www.baeldung.com/java-binary-tree
public class Node {
    int value;
    Node left;
    Node right;
    Node parent;

    Node(int value) {
        this.value = value;
        right = null;
        left = null;
        parent = null;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}

