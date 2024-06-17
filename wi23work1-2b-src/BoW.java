import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;

// Work1-2b
class BoW {
	static Tokenizer tokenizer = new Tokenizer(); // 初期化が遅いので再利用する。
	HashMap<String, Integer> termCount = new HashMap<>();

	// Work1-2b
	static BoW create(String text) {
		// 1. textを形態素解析する
		// 2. 不用語を除去する
		// 3. BoW を生成する
	}

	// Work1-2b
	static BoW fetch(String url) {
		try {
			// 実装してみよう
			// 1. urlからHTMLコンテンツの本文を取得する。
			// 2. BoWクラスを初期化して bow 変数に格納する。
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
		
	// Work1-2b
	BoW(List<Token> tokens) {
		for (Token token: tokens) {
			// 1. 語の表現形式を統一する
			String term = BoW.repr(token);

			// 2. termCount 中の語 term をカウントアップ
		}
	}

	public String toString() {
		return this.termCount.toString();
	}
	
	static String repr(Token token) {
		return token.getBaseForm();
	}
}

// Work1-2b
// 不要語については各自検討せよ。
class StopWords {
	static HashSet<String> contentWords = new HashSet<>() {{
		add("名詞");
		add("動詞");
		add("形容詞");
	}};
	
	static boolean isStopWord(Token token) {
		return (StopWords.contentWords.contains(token.getPartOfSpeechLevel1()) == false);
	}
}
