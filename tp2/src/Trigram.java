import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Trigram {
    Map<String, Set<String>> trigrammes = new HashMap<>();

    static Set<String> genererTrigrammes(String mot) {
        Set<String> trigrammes = new HashSet<>();
        for (int i = 0; i < mot.length() - 2; i++) {
            trigrammes.add(mot.substring(i, i + 3));
        }
        return trigrammes;
    }
    void   trigram(String word){
        String wordAllong = "<"+word+">";
        Set<String> trigrams=genererTrigrammes(wordAllong);
        for (String trigramme : trigrams) {
            trigrammes.computeIfAbsent(trigramme, k -> new HashSet<>()).add(word);
        }

    }
    public Map<String, Set<String>> getTrigrammes() {
        return trigrammes;
    }
    @Override
    public String toString() {
        return "Trigram{" +
                "trigrammes=" + trigrammes +
                '}';
    }

    public boolean contains(String el) {
        return trigrammes.containsKey(el);
    }

}
