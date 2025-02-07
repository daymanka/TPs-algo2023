import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {


    public Graph<String> parse(String filename) {
        int numVariables = 0;
        int numClauses = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("p cnf")) {
                    String[] headerTokens = line.split("\\s+");
                    numVariables = Integer.parseInt(headerTokens[2]);
                    numClauses = Integer.parseInt(headerTokens[3]);
                    break;
                }
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graph<String> graph = new Graph<>(2 * numVariables);

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("c") && !line.startsWith("p")) {
                    processClause(line, graph, numVariables);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    private void processClause(String line, Graph<String> graph, int numVariables) {
        String[] literals = line.split("\\s+");
        int lastIndex = literals.length - 1;

        // Ajouter des arêtes au graphe pour chaque paire de littéraux dans la clause
        for (int i = 0; i < lastIndex; i++) {
            int literal1 = Integer.parseInt(literals[i]);
            for (int j = i + 1; j < lastIndex; j++) {
                int literal2 = Integer.parseInt(literals[j]);
                addEdges(graph, literal1, literal2, numVariables);
            }
        }
    }

    private void addEdges(Graph<String> graph, int literal1, int literal2, int numVariables) {
        try {
            graph.addArc(encodeLiteral(negationLiteral(literal1), numVariables), encodeLiteral(literal2, numVariables),
                    literal1 + " " + literal2);
            graph.addArc(encodeLiteral(negationLiteral(literal2), numVariables),
                    encodeLiteral(literal1, numVariables), literal2 + " " + literal1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fonction de codage : { -k, ..., -1 } ∪ { 1, ..., k } -> { 0, ..., 2k - 1 }
    private int encodeLiteral(int literal, int numVariables) {
        if (literal < 0) {
            return -literal - 1;
        } else {
            return literal + numVariables - 1;
        }
    }

    // Fonction de décodage : { 0, ..., 2k - 1 } -> { -k, ..., -1 } ∪ { 1, ..., k }
    private int decodeLiteral(int encodedLiteral, int numVariables) {
        if (encodedLiteral < numVariables) {
            return -(encodedLiteral + 1);
        } else {
            return encodedLiteral - numVariables + 1;
        }
    }

    private int negationLiteral(int literal) {
        return (literal > 0) ? -literal : -literal - 1;
    }


}
