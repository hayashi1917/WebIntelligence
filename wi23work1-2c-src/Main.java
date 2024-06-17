import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		SpaceTest.test1();
	}
}

//Work1-2c
class SpaceTest {
	static void test1() {
		ArrayList<BoW> bows = new ArrayList<>() {{
			add(BoW.create("私はりんごとみかんは好きだが，いちごはそうでもない。"));
			add(BoW.create("あなたの名前はみかんです。"));
			add(BoW.create("僕はいちごを愛している。"));
		}};

		Space space = new Space(bows);
		System.out.println(space);
	}
}
