package graphs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Multigraph {
    private final HashMap<String, Node> nodes = new HashMap<>();

    public void addNode(String id) {
        if (nodes.containsKey(id)) {
            return;
        }

        Node node = new Node(id);
        nodes.put(id, node);
    }

    public void connectNodes(String firstNodeID, String secondNodeID) {
        if (nodes.containsKey(firstNodeID) && nodes.containsKey(secondNodeID)) {
            Node firstNode = nodes.get(firstNodeID);
            Node secondNode = nodes.get(secondNodeID);

            firstNode.connectNode(secondNode);
            secondNode.connectNode(firstNode);
        }
    }

    public void connectNodes(String firstNodeID, String secondNodeID, int edgeCount) {
        if (edgeCount <= 0) {
            return;
        }

        if (!nodes.containsKey(firstNodeID) || !nodes.containsKey(secondNodeID)) {
            return;
        }

        Node firstNode = nodes.get(firstNodeID);
        Node secondNode = nodes.get(secondNodeID);

        firstNode.connectNode(secondNode, edgeCount);
        secondNode.connectNode(firstNode, edgeCount);
    }

    public void contractNodes(String firstNodeID, String secondNodeID) {
        if (nodes.containsKey(firstNodeID) && nodes.containsKey(secondNodeID)) {
            Node firstNode = nodes.get(firstNodeID);
            Node secondNode = nodes.get(secondNodeID);

            firstNode.disconnectNode(secondNode);
            secondNode.disconnectNode(firstNode);

            nodes.remove(firstNodeID);
            nodes.remove(secondNodeID);

            String newNodeID = firstNodeID.concat(secondNodeID);
            while (nodes.containsKey(newNodeID)) {
                newNodeID = newNodeID.concat(newNodeID);
            }

            Node superNode = new Node(newNodeID);
            nodes.put(newNodeID, superNode);

            connectToSuperNode(superNode, firstNode);
            connectToSuperNode(superNode, secondNode);
        }
    }

    private void connectToSuperNode(Node superNode, Node node) {
        Set<Node> adjacentNodes = node.getAdjacentNodes();

        for (Node tmp : adjacentNodes) {
            Integer edgeCount = tmp.disconnectNode(node);
            node.disconnectNode(tmp);

            superNode.connectNode(tmp, edgeCount);
            tmp.connectNode(superNode, edgeCount);
        }
    }
}