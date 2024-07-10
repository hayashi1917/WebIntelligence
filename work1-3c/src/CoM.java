import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// CoM クラス
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

    public static void main(String[] args) {
        // テスト用のテキストデータを使ってBoWを作成
        BoW bow1 = BoW.create("りんご バナナ りんご");
        BoW bow2 = BoW.create("バナナ チェリー りんご");
        BoW bow3 = BoW.create("りんご チェリー");

        List<BoW> bows = List.of(bow1, bow2, bow3);

        CoM com = new CoM(bows);

        // 共起行列のテスト
        System.out.println(com.getCoOccurrence("りんご", "バナナ")); // 出力: 2
        System.out.println(com.getCoOccurrence("バナナ", "チェリー")); // 出力: 1
        System.out.println(com.getCoOccurrence("りんご", "チェリー"));  // 出力: 2
    }
}

