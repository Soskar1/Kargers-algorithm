import graphs.Edge;
import graphs.Multigraph;

import java.util.Random;

public class Karger {
    private final int repeatTimes;
    private final Multigraph multigraph;
    private final Random random = new Random();

    public Karger(int repeatTimes, Multigraph multigraph) {
        this.repeatTimes = repeatTimes;
        this.multigraph = new Multigraph(multigraph);
    }

    public Multigraph run() {
        Multigraph minCut = multigraph;

        for (int i = 0; i < repeatTimes; ++i) {
            Multigraph cut = contract(new Multigraph(multigraph));
            if (cut.getEdgeCount() < minCut.getEdgeCount()) {
                minCut = cut;
            }
        }

        return minCut;
    }

    private Multigraph contract(Multigraph multigraph) {
        while (multigraph.size() > 2) {
            Edge edge = pickRandomEdge(multigraph);
            multigraph.contractNodes(edge.getFirstNode().getID(), edge.getSecondNode().getID());
        }

        return multigraph;
    }

    private Edge pickRandomEdge(Multigraph multigraph) {
        int randomNumber = random.nextInt(multigraph.getEdgeCount());
        return multigraph.getEdge(randomNumber);
    }
}
