import java.util.Arrays;

class Feature {
	double[] values;

	Feature(double[] values) {
		this.values = values;
	}

	public String toString() {
		return Arrays.toString(this.values);
	}

	// Work1-2e
	double length() {
		double sum = 0;
		for (double value : this.values) {
			sum += value * value;
		}
		return Math.sqrt(sum);
	}

	// Work1-2e
	static double similarity(Feature v1, Feature v2) {
		double sum = 0;
		for (int i = 0; i < v1.values.length; i++) {
			sum += v1.values[i] * v2.values[i];
		}
		return sum / (v1.length() * v2.length());
	}

	// Work1-2e
	double similarity(Feature other) {
		return Feature.similarity(this, other);
	}

	// Work1-2ev 特徴ベクトルの集合Vについて､　v2...vnをv1との類似度の降順に並べ替える
	static Feature[] sort(Feature v1, Feature[] V) {
		Feature[] sorted = Arrays.copyOf(V, V.length);
		Arrays.sort(sorted, (v2, v3) -> Double.compare(v3.similarity(v1), v2.similarity(v1)));
		return sorted;
	}
}