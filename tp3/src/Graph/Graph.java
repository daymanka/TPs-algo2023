package Graph;

import java.util.ArrayList;
import java.util.LinkedList;


public class Graph {
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe

    public int order;
    public int upperBound;
    int edgeCardinality;

    ArrayList<LinkedList<Edge>> incidency;
    ArrayList<LinkedList<Arc>> inIncidency;
    ArrayList<LinkedList<Arc>> outIncidency;

    public Graph(int upperBound) {
        // Au début, upperBound==order
        // Ensuite, on pourrait retirer des sommets du graphe.
        // Ainsi, on pourrait avoir upperBound > order
        // Cette modification de la classe devient nécessaire
        // si vous implémentez la contraction d’arêtes
        // Autrement, on pourra asssumer que upperBound==order.

        // à compléter
        this.order = upperBound;
        this.upperBound = upperBound;
        this.edgeCardinality = 0;

        // Initialisation des listes d'adjacence
        incidency = new ArrayList<>(upperBound);
        inIncidency = new ArrayList<>(upperBound);
        outIncidency = new ArrayList<>(upperBound);

        for (int i = 0; i < upperBound; i++) {
            incidency.add(new LinkedList<>());
            inIncidency.add(new LinkedList<>());
            outIncidency.add(new LinkedList<>());
        }


    }

    public boolean isVertex(int vertex) {

        return vertex >= 0 && vertex < upperBound;
    }

    public void addVertex(int vertex) {

        if (!isVertex(vertex)) {
            ensureVertex(vertex);

            incidency.add(new LinkedList<>());
            inIncidency.add(new LinkedList<>());
            outIncidency.add(new LinkedList<>());

            upperBound++;
        }
    }


    public void deleteVertex(int vertex){

        if (isVertex(vertex)) {
            incidency.remove(vertex);
            inIncidency.remove(vertex);
            outIncidency.remove(vertex);

            // Supprimer les arêtes liées au sommet
            for (LinkedList<Edge> edges : incidency) {
                edges.removeIf(edge -> edge.getSource() == vertex || edge.getDest() == vertex);
            }

            // Mise à jour des informations
            upperBound--;
            order--;
            edgeCardinality--;

        }
    }

    public void ensureVertex(int vertex) {

        if (!isVertex(vertex)) {
            addVertex(vertex);
        }
    }

    public void addArc(Arc arc) {

        ensureVertex(arc.getSource());
        ensureVertex(arc.getDest());

        outIncidency.get(arc.getSource()).add(arc);
        inIncidency.get(arc.getDest()).add(arc);
    }

    public void addEdge(Edge edge) {

        ensureVertex(edge.getSource());
        ensureVertex(edge.getDest());

        incidency.get(edge.getSource()).add(edge);
        incidency.get(edge.getDest()).add(edge);

    }

    public Arc[] outEdges(int vertex) {
        // à modifier, si nécessaire

        // Pour la prochaine ligne voir
        // https://www.baeldung.com/java-collection-toarray-methods
        return outIncidency.get(vertex).toArray(new Arc[0]);
   }

    public ArrayList<Integer> sommetsVoisins(int vertex) {
        ArrayList<Integer> lesVoisins = new ArrayList<>();

        for (Arc arc : outIncidency.get(vertex)) {
            lesVoisins.add(arc.getDest());
        }

        return lesVoisins;
    }

}
