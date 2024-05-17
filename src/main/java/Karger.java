import graphs.Multigraph;

public class Karger {
    private final int repeatTimes;
    private final Multigraph multigraph;

    public Karger(int repeatTimes, Multigraph multigraph) {
        this.repeatTimes = repeatTimes;
        this.multigraph = new Multigraph(multigraph);
    }

    public Multigraph run() {
        Multigraph minCut = multigraph;

        for (int i = 0; i < repeatTimes; ++i) {
            Multigraph cut = contract(new Multigraph(multigraph));
            // TODO: get edge count from graph
            // TODO: update minCut if cut.edgeCount < minCut.edgeCut;
        }

        return minCut;
    }

    private Multigraph contract(Multigraph multigraph) {
        // TODO: implement
        return multigraph;
    }
}
