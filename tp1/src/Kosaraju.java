import java.util.*;

public class Kosaraju {

    private Graph<String> graph;

    public Kosaraju(Graph<String> graph) {
        this.graph = graph;
    }

    public int[] sccs(int numVariables) throws Exception {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[graph.order()];

        // Première traversée DFS pour obtenir l'ordre des sommets

        for (int i = 0; i < graph.order(); i++) {
            if (!visited[i]) {
                dfsFirst(i, visited, order);
            }
        }

        // Transposer le graphe

        Graph<String> transposedGraph = graph.transposeGraph();


        // Deuxième traversée DFS sur le graphe transposé pour obtenir les composantes fortement connexes

        Arrays.fill(visited, false);
        int[] sccs = new int[graph.order()];
        int sccId = 0;

        for (int i = order.size() - 1; i >= 0; i--) {
            int vertex = order.get(i);
            if (!visited[vertex]) {
                dfsSecond(transposedGraph, vertex, visited, sccs, sccId, numVariables);
                sccId++;
            }
        }

        return sccs;
    }

    private void dfsFirst(int vertex, boolean[] visited, List<Integer> order) {
        visited[vertex] = true;
        for (Graph<String>.Edge edge : graph.getIncidency().get(vertex)) {
            if (!visited[edge.destination]) {
                dfsFirst(edge.destination, visited, order);
            }
        }
        order.add(vertex);
    }

    private void dfsSecond(Graph<String> graph, int vertex, boolean[] visited, int[] sccs, int sccId, int numVariables) {
        visited[vertex] = true;
        sccs[vertex] = sccId;

        for (Graph<String>.Edge edge : graph.getIncidency().get(vertex)) {
            if (!visited[edge.destination]) {
                dfsSecond(graph, edge.destination, visited, sccs, sccId, numVariables);
            }
        }
    }
}
