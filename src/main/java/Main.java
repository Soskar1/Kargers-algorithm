import graphs.Multigraph;
import utils.Pair;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Multigraph multigraph = new Multigraph();
        multigraph.addNode("A");
        multigraph.addNode("B");
        multigraph.addNode("C");
        multigraph.addNode("D");

        multigraph.connectNodes("A", "B");
        multigraph.connectNodes("A", "D");
        multigraph.connectNodes("A", "C", 2);
        multigraph.connectNodes("C", "B");
        multigraph.connectNodes("C", "D");

        Karger karger = new Karger(10, multigraph);
        Multigraph minCut = karger.run();

        System.out.println("Hello");
    }
}
