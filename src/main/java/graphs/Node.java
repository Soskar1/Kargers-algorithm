package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Node {
    private final String id;
    private final HashMap<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(String id) {
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public void connectNode(Node node) {
        if (adjacentNodes.containsKey(node)) {
            adjacentNodes.replace(node, adjacentNodes.get(node) + 1);
        } else {
            adjacentNodes.put(node, 1);
        }
    }

    public void connectNode(Node node, int edgeCount) {
        if (edgeCount <= 0) {
            return;
        }

        if (adjacentNodes.containsKey(node)) {
            adjacentNodes.replace(node, adjacentNodes.get(node) + edgeCount);
        } else {
            adjacentNodes.put(node, edgeCount);
        }
    }

    public Integer disconnectNode(Node node) {
        return adjacentNodes.remove(node);
    }

    public Set<Node> getAdjacentNodes() {
        return new HashSet<>(adjacentNodes.keySet());
    }

    public Integer getEdgeCount(Node node) {
        if (adjacentNodes.containsKey(node)) {
            return adjacentNodes.get(node);
        }

        return 0;
    }
}
