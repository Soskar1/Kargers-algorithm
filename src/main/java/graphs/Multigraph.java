package graphs;

import java.util.HashMap;

public class Multigraph<T> {
    private final HashMap<T, Node<T>> nodes = new HashMap<>();

    public void addNode(T id) {
        if (nodes.containsKey(id)) {
            return;
        }

        Node<T> node = new Node<>(id);
        nodes.put(id, node);
    }

    public void connectNodes(T firstNodeID, T secondNodeID) {
        if (nodes.containsKey(firstNodeID) && nodes.containsKey(secondNodeID)) {
            Node<T> firstNode = nodes.get(firstNodeID);
            Node<T> secondNode = nodes.get(secondNodeID);

            firstNode.connectNode(secondNode);
            secondNode.connectNode(firstNode);
        }
    }

    public void connectNodes(T firstNodeID, T secondNodeID, int edgeCount) {
        if (edgeCount <= 0) {
            return;
        }

        if (!nodes.containsKey(firstNodeID) || !nodes.containsKey(secondNodeID)) {
            return;
        }

        Node<T> firstNode = nodes.get(firstNodeID);
        Node<T> secondNode = nodes.get(secondNodeID);

        firstNode.connectNode(secondNode, edgeCount);
        secondNode.connectNode(firstNode, edgeCount);
    }
}