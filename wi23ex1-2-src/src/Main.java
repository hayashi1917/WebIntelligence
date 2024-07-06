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
		for(int j = 0; j < space.M[0].length; j++){
			for (int i = 0; i < space.M[j].length; i++) {
				System.out.println("M[" + i + "]=" + space.M[j][i]);
			}
		}
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
		double[] sim = new double[3];
		for (int i = 0; i < 3; i++) {
			sim[i] = Feature.similarity(space.features.get(i), q);
		}
		int maxIndex = 0;
		for (int i = 1; i < 3; i++) {
			if (sim[maxIndex] < sim[i]) {
				maxIndex = i;
			}
		}
		System.out.println("最も類似度が高い記事は" + (maxIndex + 1) + "番目の記事です。");
	}
	// -->

	// 課題1-2
	static String html1() throws IOException {
		Document document = Jsoup.connect("https://news.yahoo.co.jp/articles/fb3f7fc8048aad4ebbf0794f28fd023eb30f634e").get();
		return document.body().text();
	}

	// 課題1-2
	static String html2() throws IOException {
		Document document = Jsoup.connect("https://news.yahoo.co.jp/articles/ef54c4732947fac4c059fffa05567314867407dc").get();
		return document.body().text();
	}

	// 課題1-2
	static String html3() throws IOException {
		Document document = Jsoup.connect("https://news.yahoo.co.jp/articles/085be9f6da696f346fe77a9dd3a5eeac3c7b458e").get();
		return document.body().text();
	}
}