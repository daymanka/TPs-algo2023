import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
    private Tree tree = new Tree();
    private Trigram trigramme=new Trigram();

    public Dictionary(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tree.insert(line);
                trigramme.trigram(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Tree getTree() {
        return tree;
    }

    Trigram getTrigramme(){
        return trigramme;
    }
    public boolean search(String word) {
        return tree.search(word);
    }
}
