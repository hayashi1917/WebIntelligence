import java.util.*;
import java.util.stream.Collectors;
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

    public void draw(int C) {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Co-Occurrence Network");

        // ノードとエッジを追加
        for (String word : coMatrix.keySet()) {
            Map<String, Integer> innerMap = coMatrix.get(word);
            for (String coWord : innerMap.keySet()) {
                if (innerMap.get(coWord) >= C) {
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
    	int C = 2;
    	String text = "JR中央本線鶴舞駅にほど近い良い立地ということもあり、東海地方各地の高校から進学する学生が比較的多い。さらに卒業生のうち多くが大手製造業に就職する。中でも地元企業に強く、トヨタグループまたは関連会社への就職が目立つ。その他、輸送機器、電機、重工業、化学など様々な分野の産業へ就職する。学科にもよるが卒業生の約8割が大学院（博士前期課程）へ進学する。博士後期課程まで進むのは約1割である。正門付近には、名古屋工業大学で研究している音声言語処理技術をもとに開発された双方向音声案内デジタルサイネージを設置している。大型ディスプレイに登場する3Dキャラクター「メイちゃん」が来校者の音声を認識し学内を案内するという、世界初の全天候型デジタルサイネージである。大学の土地面積はやや狭く駐車場が小さいため、学生は基本的に自動車では通学できない。最近では学内組織（環境委員会）の働きで学内環境は改善の方向に向かっている。学生の男女比は、男子が約8割強。女子推薦入学などの影響もあり、近年は女子の割合が増えてきている。学生食堂は大学会館1階の生協大食堂と、2階のカフェテリアがある。また、大学の周辺には安価な食堂が数軒ある。2005年5月、近辺にイオン千種ショッピングセンター（現・イオンタウン千種。マックスバリュ）ができ、ファストフード店などに行く学生もいる。学生食堂については、2012年4月に改修された大学会館がオープン。1階の大食堂、2階のカフェテリアともに大幅に席数が増加し、以前のような混雑は解消された。メニューにも学生の声を生かす工夫がされている。学内には大学生協運営のコンビニエンスストアが3つあり、夜10時まで営業。大学会館改修中に1号館内に設置されたミニコンビニも継続している。また、大学に隣接した場所にコンビニエンスストアがあり、イオンタウン千種も24時間利用できるため、研究室の徹夜組には便利な存在となっている。東京理科大学や電気通信大学などと並び比較的留年が多い大学とされる。構内には一本松古墳が存在し、その「古墳に登ると留年する（あるいは単位を落とす）」という学生に伝わる伝説がある。大学のすぐ隣にある鶴舞公園は名古屋でも有数の花見の名所であり、毎年花見のシーズンになると鶴舞公園で宴会を行う学生も多い。研究室に配属されたばかりの4年生は、研究室によっては場所取りをすることもある。";
    	
        List<BoW> bows = BoW.createFromSentences(text);
        
        CoM com = new CoM(bows);
        com.draw(C); // ここで共起頻度の閾値を設定
    }
}

