import graphs.Multigraph;

public class Main {
    public static void main(String[] args) {
        Multigraph multigraph = new Multigraph();

        multigraph.addNode("A");
        multigraph.addNode("B");
        multigraph.addNode("C");
        multigraph.addNode("D");

        multigraph.connectNodes("A", "B");
        multigraph.connectNodes("A", "C");
        multigraph.connectNodes("A", "D", 2);

        multigraph.connectNodes("C", "D");
        multigraph.connectNodes("D", "B");

        Multigraph multigraph2 = new Multigraph(multigraph);
        //multigraph.contractNodes("A", "B");

        System.out.println("Hello");
    }
}
