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

        System.out.println(multigraph.getEdgeCount());
        System.out.println(multigraph.size());

        System.out.println("------");
        multigraph.contractNodes("C", "D");
        System.out.println(multigraph.getEdgeCount());
        System.out.println(multigraph.size());

        System.out.println("------");
        multigraph.contractNodes("CD", "A");
        System.out.println(multigraph.getEdgeCount());
        System.out.println(multigraph.size());
    }
}
