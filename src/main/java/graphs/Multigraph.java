package graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Multigraph {
    private final HashMap<String, Node> nodes = new HashMap<>();

    public Multigraph() { }

    public Multigraph(Multigraph multigraph) {
        for (Map.Entry<String, Node> entry : multigraph.nodes.entrySet()) {
            String key = entry.getKey();
            Node originalNode = entry.getValue();

            Node nodeCopy = new Node(key);
            nodes.put(key, nodeCopy);

            Set<Node> adjacentNodes = originalNode.getAdjacentNodes();
            for (Node node : adjacentNodes) {
                if (!nodes.containsKey(node.getID())) {
                    nodes.put(node.getID(), new Node(node.getID()));
                }

                Node adjacentNodeCopy = nodes.get(node.getID());
                Integer edgeCount = node.getEdgeCount(originalNode);

                nodeCopy.connectNode(adjacentNodeCopy, edgeCount);
            }
        }
    }

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