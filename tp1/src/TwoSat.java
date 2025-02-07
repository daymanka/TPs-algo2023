
public class TwoSat {

    public static boolean checkConsistency(int[] sccs, int numVariables) {
        int n =  numVariables;

        // Vérifiez que la longueur du tableau est suffisante

        if (sccs.length < n) {
            throw new IllegalArgumentException("Le tableau sccs n'a pas la longueur attendue.");
        }

        for (int i = 0; i < n/2; i += 2) {
            // Si les deux littéraux de la variable i sont dans la même composante fortement connexe,
            // alors la formule n'est pas satisfaisable
            if (sccs[encodeLiteral(i, numVariables/2)] == sccs[encodeLiteral(i+1 , numVariables/2)]) {
                return false;
            }
        }
        return true;
    }

    private static int encodeLiteral(int literal, int numVariables) {
        if (literal < 0) {
            return -literal - 1;
        } else {
            return literal + numVariables - 1;
        }
    }
}
