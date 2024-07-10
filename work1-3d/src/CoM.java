import java.util.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

class CoM {
    private Map<String, Map<String, Integer>> coMatrix;

    public CoM(List<BoW> bows) {
        coMatrix = new HashMap<>();
        for (BoW bow : bows) {
            add(bow);
        }
    }

    public void add(BoW bow) {
        List<String> words = new ArrayList<>(bow.termCount.keySet());
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                String word1 = words.get(i);
                String word2 = words.get(j);
                incrementCount(word1, word2);
                incrementCount(word2, word1);
            }
        }
    }

    private void incrementCount(String word1, String word2) {
        coMatrix.putIfAbsent(word1, new HashMap<>());
        Map<String, Integer> innerMap = coMatrix.get(word1);
        innerMap.put(word2, innerMap.getOrDefault(word2, 0) + 1);
    }

    public int getCoOccurrence(String word1, String word2) {
        return coMatrix.getOrDefault(word1, new HashMap<>()).getOrDefault(word2, 0);
    }

    public void draw() {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Co-Occurrence Network");

        // ノードとエッジを追加
        for (String word : coMatrix.keySet()) {
            Map<String, Integer> innerMap = coMatrix.get(word);
            for (String coWord : innerMap.keySet()) {
                if (innerMap.get(coWord) >= 2) {
                    if (graph.getNode(word) == null) {
                        graph.addNode(word).addAttribute("ui.label", word);
                    }
                    if (graph.getNode(coWord) == null) {
                        graph.addNode(coWord).addAttribute("ui.label", coWord);
                    }
                    String edgeId = word.compareTo(coWord) < 0 ? word + "-" + coWord : coWord + "-" + word;
                    if (graph.getEdge(edgeId) == null) {
                        graph.addEdge(edgeId, word, coWord);
                    }
                }
            }
        }

        // グラフを表示
        graph.display();
    }

    public static void main(String[] args) {
        BoW bow1 = BoW.create("りんご バナナ りんご");
        BoW bow2 = BoW.create("バナナ チェリー りんご");
        BoW bow3 = BoW.create("りんご チェリー");

        List<BoW> bows = List.of(bow1, bow2, bow3);

        CoM com = new CoM(bows);

        com.draw();
    }
}



