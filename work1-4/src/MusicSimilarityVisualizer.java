import java.util.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class MusicSimilarityVisualizer {
    public static void main(String[] args) {
        // 1. データ収集
        List<String> urls = Arrays.asList(
            "https://ameblo.jp/kar1206/entry-12853391730.html",
            "https://utaten.com/lyric/hw21051901/",
            "https://www.uta-net.com/song/356731/"
        ); 
        
        // ユーザーが入力する曲名リスト
        List<String> songTitles = Arrays.asList(
            "supernova",
            "NEXT LEBEL",
            "Hot Mess"
        );
        
        // URLと曲名の数が一致することを確認
        if (urls.size() != songTitles.size()) {
            System.out.println("エラー: URLと曲名の数が一致しません。");
            return;
        }

        ArrayList<BoW> bows = new ArrayList<>();
        for (String url : urls) {
            BoW bow = BoW.fetch(url);
            if (bow != null) {
                bows.add(bow);
            }
        }

        // 2. 特徴抽出
        Space space = new Space(bows);
        List<Feature> features = space.features;

        // 3. グラフ作成
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Music Similarity Network");

        // 4. ノードとエッジの追加
        for (int i = 0; i < features.size(); i++) {
            Node node = graph.addNode("Song" + i);
            node.setAttribute("ui.label", songTitles.get(i));
            node.setAttribute("ui.style", "text-size: 20px; size: 30px; fill-color: rgb(100,100,255);");
            
            for (int j = i + 1; j < features.size(); j++) {
                double similarity = Feature.similarity(features.get(i), features.get(j));
                if (similarity > 0.3) { // 類似度のしきい値
                    Edge edge = graph.addEdge("Song" + i + "-Song" + j, "Song" + i, "Song" + j);
                    edge.setAttribute("ui.label", String.format("%.2f", similarity));
                    edge.setAttribute("ui.style", "text-size: 15px;");
                }
            }
        }

        // 5. グラフの表示
        graph.display();
    }
}