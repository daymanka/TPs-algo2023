import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.*;

public class SpellChecker {
    private class WordDistancePair{
        private String word;
        private int distance;

        public WordDistancePair(String word, int distance) {
            this.word = word;
            this.distance = distance;
        }

        public String getWord() {
            return word;
        }

        public int getDistance() {
            return distance;
        }
    }

    private ArrayList<String> words = new ArrayList<String>();
    private Dictionary dictionary = null;

    /**
     * On essaie d'ouvrir le fichier pathToFile contenant les mots à corriger
     * Si erreur, on utilise seulement word
     *
     * @param pathToFile
     * @param word
     */
    public SpellChecker(String pathToFile, String word, Dictionary dictionary) {
        this.dictionary=dictionary;
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            words.add(word);
        }
    }


    /**
     * Regarde si word est dans le dictionnaire
     * sinon il affiche une liste de mot les plus proches
     *
     * @param word
     */
    public void spell(String word) {
        if(dictionary.search(word)){
            System.out.println("Le mot "+word+ " est bien orthographie");
            return;
        }

        Set<String> set= Trigram.genererTrigrammes(word);

        List<String> all = new ArrayList<>();

        Trigram trigram = dictionary.getTrigramme();
        for (String el : set){
            if(trigram.contains(el)){
                Set<String> list = trigram.getTrigrammes().get(el);
                all.addAll(list);
            }

        }
        //Compter la frequence
        Map<String,Integer> frequenceMot = new HashMap<>();
        for(String mot : all){
            frequenceMot.put(mot,frequenceMot.getOrDefault(mot,0)+1);}

        //Trier les mots par frequence decroissante

        List<String> sortedMots = new ArrayList<>(frequenceMot.keySet());
        sortedMots.sort((w1,w2) ->Integer.compare(frequenceMot.get(w2),frequenceMot.get(w1)));
        // selectionner les premiers mots

        List<String> selection = sortedMots.subList(0,Math.min(100,sortedMots.size()))  ;

        List<WordDistancePair> distances = new ArrayList();

        for(String mot:selection){
            int levenshteinDistance = Levenshtein.distance(mot,word);
            distances.add(new WordDistancePair(mot,levenshteinDistance));
        }
        distances.sort((pair1,pair2)->Integer.compare(pair1.distance, pair1.distance ));

        //afficher les 5 premiers suggestions
        System.out.println("Voici les 5 mots suggerees pour la correction du mot  '" + word+"'");
        int count = 0;
        for(WordDistancePair pair: distances){
            if(count<5){
                System.out.println(pair.word);
                count++;
            }
            else break;
        }

    }

    /**
     * Retourne la liste de tous les mot dont
     * il faut faire la correction
     *
     * @return
     */
    public ArrayList<String> getWords() {
        return words;
    }
}
