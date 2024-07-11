import java.util.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class MusicSimilarityVisualizer {
    public static void main(String[] args) {
        // 1. データ収集
        Map<String, String> songUrlMap = new LinkedHashMap<>();
        songUrlMap.put("King Gnu BOY", "https://www.uta-net.com/song/309087/");
        songUrlMap.put("King Gnu ユーモア", "https://www.uta-net.com/song/279876/");
        songUrlMap.put("King Gnu PrayerX", "https://www.uta-net.com/song/255819/");
        songUrlMap.put("King Gnu Flash!!!", "https://www.uta-net.com/song/262414/");
        songUrlMap.put("King Gnu Vinyl", "https://www.uta-net.com/song/238326/");
        songUrlMap.put("King Gnu 飛行艇", "https://www.uta-net.com/song/272239/");
        songUrlMap.put("King Gnu 白日", "https://www.uta-net.com/song/263550/");
        songUrlMap.put("King Gnu 逆夢", "https://www.uta-net.com/song/312609/");
        songUrlMap.put("King Gnu 一途", "https://www.uta-net.com/song/312149/");
        
        /*
        songUrlMap.put("aespa SunandMoon", "https://www.uta-net.com/song/356730/");
        songUrlMap.put("aespa HotMess", "https://www.uta-net.com/song/356730/");
        songUrlMap.put("IVE LOVEDIVE", "https://www.uta-net.com/song/330919/");
        songUrlMap.put("IVE AfterLIKE", "https://www.uta-net.com/song/337982/");
        songUrlMap.put("IVE ELEVEN", "https://www.uta-net.com/song/325185/");
        */
        

        ArrayList<BoW> bows = new ArrayList<>();
        for (String url : songUrlMap.values()) {
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
        List<String> songTitles = new ArrayList<>(songUrlMap.keySet());
        for (int i = 0; i < features.size(); i++) {
            Node node = graph.addNode("Song" + i);
            node.setAttribute("ui.label", songTitles.get(i));
        }

        for (int i = 0; i < features.size(); i++) {
            for (int j = i + 1; j < features.size(); j++) {
                double similarity = Feature.similarity(features.get(i), features.get(j));
                if (similarity > 0.05) { // 類似度のしきい値
                    Edge edge = graph.addEdge("Song" + i + "-Song" + j, "Song" + i, "Song" + j);
                    edge.setAttribute("ui.label", String.format("%.2f", similarity));
                }
            }
        }

        // 5. グラフの表示
        graph.display();
    }
}