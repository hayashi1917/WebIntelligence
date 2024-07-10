import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;

class BoW {
    static Tokenizer tokenizer = new Tokenizer(); // 初期化が遅いので再利用する。
    HashMap<String, Integer> termCount = new HashMap<>();

    static BoW create(String text) {
        // 1. textを形態素解析する
        List<Token> tokens = tokenizer.tokenize(text);
        // 2. 不用語を除去する
        tokens.removeIf(n -> StopWords.isStopWord(n));
        // 3. BoW を生成する
        return new BoW(tokens);
    }

    static List<BoW> createFromSentences(String text) {
        // 文単位に分割する（簡易的に句読点で分割）
        String[] sentences = text.split("。|、");
        List<BoW> bows = new ArrayList<>();
        for (String sentence : sentences) {
            if (!sentence.trim().isEmpty()) {
                bows.add(create(sentence.trim()));
            }
        }
        return bows;
    }

    BoW(List<Token> tokens) {
        for (Token token : tokens) {
            // 1. 語の表現形式を統一する
            String term = BoW.repr(token);
            // 2. termCount 中の語 term をカウントアップ
            termCount.put(term, termCount.getOrDefault(term, 0) + 1);
        }
    }

    static String repr(Token token) {
        return token.getBaseForm();
    }

    public String toString() {
        return this.termCount.toString();
    }
}

class StopWords {
    static HashSet<String> contentWords = new HashSet<>() {{
        add("名詞");
        add("動詞");
        add("形容詞");
    }};

    static boolean isStopWord(Token token) {
        return !contentWords.contains(token.getPartOfSpeechLevel1());
    }
}
