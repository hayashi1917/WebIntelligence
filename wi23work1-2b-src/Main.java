public class Main {
	public static void main(String[] args) {
		BoWTest.test1();
	}
}

//Work1-2b
class BoWTest {
	static void test1() {
		BoW bow = BoW.create("暑い夏は嫌いです。");
		System.out.println(bow);
	}

	static void test2() {
		BoW bow = BoW.fetch("https://ja.wikipedia.org/");
		System.out.println(bow);
	}
}
