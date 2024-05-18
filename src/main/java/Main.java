import graphs.Multigraph;

public class Main {
    public static void main(String[] args) {
        Multigraph multigraph = new Multigraph();
        multigraph.addNode("A");
        multigraph.addNode("B");
        multigraph.addNode("C");
        multigraph.addNode("D");
        multigraph.addNode("E");
        multigraph.addNode("F");
        multigraph.addNode("G");
        multigraph.addNode("H");

        multigraph.connectNodes("A", "B", 2);
        multigraph.connectNodes("A", "D");
        multigraph.connectNodes("A", "C");
        multigraph.connectNodes("B", "D");
        multigraph.connectNodes("B", "C");
        multigraph.connectNodes("C", "D");
        multigraph.connectNodes("C", "F");
        multigraph.connectNodes("D", "E");
        multigraph.connectNodes("D", "H");
        multigraph.connectNodes("E", "F");
        multigraph.connectNodes("E", "G");
        multigraph.connectNodes("E", "H");
        multigraph.connectNodes("F", "H");
        multigraph.connectNodes("F", "G");
        multigraph.connectNodes("H", "G", 2);

        Karger karger = new Karger(10, multigraph);
        Multigraph minCut = karger.run();

        System.out.println("Hello");
    }
}
