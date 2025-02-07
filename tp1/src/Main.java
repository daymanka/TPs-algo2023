import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) throws Exception {

        String filename = "formulas/testSet0/formula3.txt";
        if (0 < args.length) {
            filename = args[0];
        }

        Parser parser = new Parser();
        Graph<String> graph = parser.parse(filename);
        Kosaraju k = new Kosaraju(graph);
        int[] composantes = k.sccs(graph.order());


        if (TwoSat.checkConsistency(composantes,graph.order())) {
            System.out.println("Formula " + filename + ": satisfiable");
            exit(0);
        } else {
            System.out.println("Formula " + filename + ": unsatisfiable");
            exit(-1);
        }
        exit(0);

    }

}
