import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	public static void main(String[] args) throws IOException {
		example1();
	}

	// Work1-2a
	static void example1() throws IOException {
		Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
		System.out.println(doc.html());
		System.out.println(doc.title());
		System.out.println(doc.body().text());
	}

	// Work1-2a
	static void example2() throws IOException {
		Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
		Elements newsHeadlines = doc.select("#mp-itn b a");
		for (Element headline : newsHeadlines) {
			System.out.println(headline.attr("title"));
			System.out.println(headline.absUrl("href"));
		}
	}
}