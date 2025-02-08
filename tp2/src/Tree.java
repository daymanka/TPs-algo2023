import java.util.HashMap;
import java.util.Map;
public class Tree {
    Node racine;
    public Tree(){
        racine=new Node();
    }
    public void insert(String word){
        Node node =racine;
        for (int i=0;i<word.length();i++){
            char ch=word.charAt(i);
            node.children.putIfAbsent(ch,new Node());
            node=node.children.get(ch);
        }
        node.endOfWord=true;
    }
    public boolean search(String word) {
        Node node = racine;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }
        return node != null && node.endOfWord;
    }

    public class Node{
        Map<Character ,Node> children;
        boolean endOfWord;
        public Node(){
            children=new HashMap<>();
            endOfWord=false;
        }

    }
}

