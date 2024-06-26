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
}
