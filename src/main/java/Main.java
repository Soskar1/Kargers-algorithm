import graphs.Multigraph;

public class Main {
    public static void main(String[] args) {
        Multigraph<Integer> multigraph = new Multigraph<>();

        multigraph.addNode(1);
        multigraph.addNode(2);
        multigraph.addNode(3);

        multigraph.connectNodes(1, 2, 4);
        multigraph.connectNodes(2, 3, 5);
        multigraph.connectNodes(1, 3);
    }
}
