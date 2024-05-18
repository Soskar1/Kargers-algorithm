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

        //multigraph.contractNodes("A", "C");

        //Karger karger = new Karger(10, multigraph);
        //Multigraph minCut = karger.run();

        Multigraph multigraph2 = new Multigraph(multigraph);
        multigraph2.contractNodes("A", "C");

        //System.out.println(multigraph2.size());
        //System.out.println(multigraph2.getEdgeCount());

        System.out.println("Hello");
    }
}
