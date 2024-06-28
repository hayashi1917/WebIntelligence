import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {
	public static void main(String[] args) throws IOException {
		Ex2Test.test1();
	}
}

class Ex2Test {
	// 課題1-2
	// <!--
	static void test1() throws IOException {
		ArrayList<BoW> bows = new ArrayList<>() {{
			add(BoW.create(html1()));
			add(BoW.create(html2()));
			add(BoW.create(html3()));
		}};

		Space space = new Space(bows);

		Feature v1 = space.features.get(0);
		Feature v2 = space.features.get(1);
		Feature v3 = space.features.get(2);
		System.out.println(Feature.similarity(v1, v2));
		System.out.println(Feature.similarity(v2, v3));
		System.out.println(Feature.similarity(v3, v1));

		Feature q = space.translate("チャットGPTは質問や命令を入力すると、自然な文章で回答したり提案書を作成したりする。");
		System.out.println(q);
		System.out.println(Feature.similarity(v1, q));
		System.out.println(Feature.similarity(v2, q));
		System.out.println(Feature.similarity(v3, q));

		// 課題の機能を実装すること。
	}
	// -->

	// 課題1-2
	static String html1() throws IOException {
		Document document = Jsoup.connect("https://news.yahoo.co.jp/articles/c58fd4a9dfd9c97e1cc307cc13f372ef7f7aac09").get();
		return document.body().text();
	}

	// 課題1-2
	static String html2() throws IOException {
		Document document = Jsoup.connect("https://news.yahoo.co.jp/articles/cfe9bb2e6037386dd675445341b1d24e1e7db0d6").get();
		return document.body().text();
	}

	// 課題1-2
	static String html3() throws IOException {
		Document document = Jsoup.connect("https://news.yahoo.co.jp/articles/cf9d0d6797204d7cf699fd2f10b8788db9b95785").get();
		return document.body().text();
	}
}