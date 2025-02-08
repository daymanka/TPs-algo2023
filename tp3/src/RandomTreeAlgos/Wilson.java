package RandomTreeAlgos;

import Graph.Arc;
import Graph.Edge;
import Graph.Graph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Random;

public class Wilson {

    private static Graph graph;
    private static Random random;

    public Wilson(Graph graph) {
        this.graph = graph;
        this.random = new Random();
    }

    public ArrayList<Arc> generateTree() {
        ArrayList<Arc> tree = new ArrayList<>();
        BitSet visited = new BitSet(graph.order);

        for (int startVertex = 0; startVertex < graph.order; startVertex++) {
            if (!visited.get(startVertex)) {
                ArrayList<Arc> path = new ArrayList<>();
                ArrayList<Integer> stack = new ArrayList<>();

                int currentVertex = startVertex;
                while (!visited.get(currentVertex)) {
                    stack.add(currentVertex);
                    visited.set(currentVertex);

                    ArrayList<Integer> neighbors = graph.sommetsVoisins(currentVertex);
                    if (neighbors.isEmpty()) {

                        path.clear();
                        stack.clear();
                        currentVertex = startVertex;
                    } else {
                        int nextVertex = neighbors.get(random.nextInt(neighbors.size()));
                        Edge edge = new Edge(currentVertex, nextVertex, 0.0);
                        path.add(new Arc(edge, false));
                        currentVertex = nextVertex;
                    }

                    // Vérifier si le sommet courant est déjà dans le chemin
                    int loopIndex = stack.indexOf(currentVertex);
                    if (loopIndex != -1) {
                        path.subList(0, loopIndex).clear();
                        stack.subList(0, loopIndex).clear();
                    }
                }

                // Ajouter les arcs du chemin à l'arbre couvrant
                tree.addAll(path);
            }
        }

        return tree;
    }
}