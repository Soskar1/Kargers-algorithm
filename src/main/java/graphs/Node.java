package graphs;

import java.util.HashMap;

public class Node<T> {
    private final T id;
    private final HashMap<Node<T>, Integer> adjacentNodes = new HashMap<>();

    public Node(T id) {
        this.id = id;
    }

    public T getID() {
        return id;
    }

    public void connectNode(Node<T> node) {
        if (adjacentNodes.containsKey(node)) {
            adjacentNodes.replace(node, adjacentNodes.get(node) + 1);
        } else {
            adjacentNodes.put(node, 1);
        }
    }

    public void connectNode(Node<T> node, int edgeCount) {
        if (edgeCount <= 0) {
            return;
        }

        if (adjacentNodes.containsKey(node)) {
            adjacentNodes.replace(node, adjacentNodes.get(node) + edgeCount);
        } else {
            adjacentNodes.put(node, edgeCount);
        }
    }
}
