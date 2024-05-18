package graphs;

import utils.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Multigraph {
    private final HashMap<String, Node> nodes = new HashMap<>();
    private final HashMap<Pair<String>, ArrayList<Edge>> edgesBetweenAdjacentNodes = new HashMap<>();
    private final ArrayList<Edge> edges = new ArrayList<>();

    public Multigraph() { }

    public Multigraph(Multigraph multigraph) {
        for (Map.Entry<String, Node> entry : multigraph.nodes.entrySet()) {
            String key = entry.getKey();
            Node originalNode = entry.getValue();

            if (!nodes.containsKey(key)) {
                nodes.put(key, new Node(key));
            }

            Node nodeCopy = nodes.get(key);

            Set<Node> adjacentNodes = originalNode.getAdjacentNodes();
            for (Node node : adjacentNodes) {
                if (!nodes.containsKey(node.getID())) {
                    nodes.put(node.getID(), new Node(node.getID()));
                }

                Node adjacentNodeCopy = nodes.get(node.getID());
                Integer edgeCount = node.getEdgeCount(originalNode);

                nodeCopy.connectNode(adjacentNodeCopy, edgeCount);

                var pair = new Pair<>(nodeCopy.getID(), adjacentNodeCopy.getID());
                if (!edgesBetweenAdjacentNodes.containsKey(pair)) {
                    for (int i = 0; i < edgeCount; ++i) {
                        addEdge(nodeCopy, adjacentNodeCopy);
                    }
                }
            }
        }
    }

    public int size() {
        return nodes.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public Edge getEdge(int index) throws IllegalArgumentException {
        if (index >= edges.size() - 1) {
            throw new IllegalArgumentException("Bad index");
        }

        return edges.get(index);
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

            addEdge(firstNode, secondNode);
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

        for (int i = 0; i < edgeCount; ++i) {
            addEdge(firstNode, secondNode);
        }
    }

    public void contractNodes(String firstNodeID, String secondNodeID) {
        if (nodes.containsKey(firstNodeID) && nodes.containsKey(secondNodeID)) {
            Node firstNode = nodes.get(firstNodeID);
            Node secondNode = nodes.get(secondNodeID);

            firstNode.disconnectNode(secondNode);
            secondNode.disconnectNode(firstNode);

            removeEdgesBetweenAdjacentNodes(firstNodeID, secondNodeID);

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

        for (Node adjacentNode : adjacentNodes) {
            Integer edgeCount = adjacentNode.disconnectNode(node);
            node.disconnectNode(adjacentNode);

            removeEdgesBetweenAdjacentNodes(node, adjacentNode);

            superNode.connectNode(adjacentNode, edgeCount);
            adjacentNode.connectNode(superNode, edgeCount);

            for (int i = 0; i < edgeCount; ++i) {
                addEdge(adjacentNode, superNode);
            }
        }
    }

    private void addEdge(Node firstNode, Node secondNode) {
        Edge edge = new Edge(firstNode, secondNode);
        edges.add(edge);
        var key = new Pair<>(firstNode.getID(), secondNode.getID());

        if (!edgesBetweenAdjacentNodes.containsKey(key)) {
            edgesBetweenAdjacentNodes.put(key, new ArrayList<>());
        }

        ArrayList<Edge> adjacentNodesEdges = edgesBetweenAdjacentNodes.get(key);
        adjacentNodesEdges.add(edge);
    }

    private void removeEdgesBetweenAdjacentNodes(String firstNodeID, String secondNodeID) {
        var key = new Pair<>(firstNodeID, secondNodeID);
        ArrayList<Edge> adjacentNodeEdges = edgesBetweenAdjacentNodes.get(key);
        for (Edge edge : adjacentNodeEdges) {
            edges.remove(edge);
        }

        adjacentNodeEdges.clear();
        edgesBetweenAdjacentNodes.remove(key);
    }

    private void removeEdgesBetweenAdjacentNodes(Node firstNode, Node secondNode) {
        removeEdgesBetweenAdjacentNodes(firstNode.getID(), secondNode.getID());
    }
}