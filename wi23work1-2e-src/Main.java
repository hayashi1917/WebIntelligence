import java.util.Random;

public class Main {
	public static void main(String[] args) {
		FeatureTest.test1();
	}
}

class FeatureTest {
	// Work1-2e
	static void test1() {
		int degree = 2;
		Feature v1 = FeatureTest.randomFeature(degree);
		Feature v2 = FeatureTest.randomFeature(degree);
		System.out.println(Feature.similarity(v1, v2));
	}

	// Work1-2e
	static void test2() {
		int degree = 3;
		int count = 10;
		Feature[] vs = new Feature[count];
		for (int i = 0; i < count; i++) {
			vs[i] = FeatureTest.randomFeature(degree);
		}

		// 課題のプログラムを書く
		

	}
	
	// Work1-2e
	// degree 次元の特徴ベクトルを返す。値はランダムに決定。
	static Feature randomFeature(int degree) {
		Random random = new Random();
		double[] values = new double[degree];
		for (int i = 0; i < degree; i++) {
			values[i] = random.nextDouble();
		}
		return new Feature(values);
	}


}
