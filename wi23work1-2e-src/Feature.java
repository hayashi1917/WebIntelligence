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

	}

	// Work1-2e
	static double similarity(Feature v1, Feature v2) {

	}
		
	// Work1-2e
	double similarity(Feature other) {
		return Feature.similarity(this, other);
	}
}
