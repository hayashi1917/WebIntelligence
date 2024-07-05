import java.util.ArrayList;
import java.util.Arrays;

// Work1-2c
class Space {
	// 索引語の初期値
	ArrayList<String> indexTerms = new ArrayList<>() {{
		add("りんご");
		add("みかん");
		add("いちご");
	}};

	ArrayList<Feature> features = new ArrayList<>();
	// Work1-2c
	int[][] M; // 単語文章行列 M[i][j] = 文書 d_i 中における語 t_j の頻度
	double[][] W; // 重み行列 W[i][j] = 文書 d_i 中における語 t_j の重み

	// Work1-2c
	// BoWのリストからベクトル空間を構成する。
	Space(ArrayList<BoW> bows) {
		// 1. 索引語の決定
		this.indexTerms = this.makeIndexTerms(bows);

		// 2. 単語文書行列を初期化
		this.M = this.makeTermDocumentMatrix(bows);
		System.out.println(Arrays.deepToString(this.M));

		// 3. 語の重みを計算する
		this.W = this.makeWeightMatrix(this.M);
		System.out.println(Arrays.deepToString(this.W));

		// 4. 特徴ベクトルを生成
		for (BoW bow : bows) {  // bows の各要素に対してループ
			int[] termCounts = new int[indexTerms.size()];
			for (int j = 0; j < indexTerms.size(); j++) {
				String term = indexTerms.get(j);
				termCounts[j] = bow.termCount.getOrDefault(term, 0);
			}

			double[] weightedTerms = new double[indexTerms.size()];
			for (int j = 0; j < indexTerms.size(); j++) {
				weightedTerms[j] = tfidf(M, 0, j) * termCounts[j]; // Mの行は常に0
			}

			Feature feature = new Feature(weightedTerms);
			this.features.add(feature);
		}
	}

	// Work1-2c, Work1-2d
	ArrayList<String> makeIndexTerms(ArrayList<BoW> bows) {
		return this.indexTerms;
	}

	// Work1-2c
	int indexOf(String term) {
		return this.indexTerms.indexOf(term);
	}

	// Work1-2c
	// 単語文章行列の生成
	int[][] makeTermDocumentMatrix(ArrayList<BoW> bows) {
		int[][] A = new int[bows.size()][this.indexTerms.size()];// 単語文章行列の計算
		for (int i = 0; i < bows.size(); i++) {
			for (int j = 0; j < this.indexTerms.size(); j++) {
				String term = this.indexTerms.get(j);
				A[i][j] = bows.get(i).termCount.getOrDefault(term, 0);
			}
		}
		return A;
	}

	// Work1-2c
	// 重み行列の生成
	double[][] makeWeightMatrix(int[][] M) {
		double[][] B = new double[M.length][this.indexTerms.size()];// 重み行列の計算
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < this.indexTerms.size(); j++) {
				B[i][j] = this.tfidf(M, i, j);
			}
		}
		return B;
	}

	// Work1-2c
	double tfidf(int[][] M, int i, int j) {
		return this.tf(M, i, j) * this.idf(M, j);
	}

	// Work1-2c
	double tf(int[][] M, int i, int j) {
		return M[i][j];
	}

	// Work1-2c
	double df(int[][] M, int j) {
		int count = 0;
		for(int i = 0; i < M.length; i++) {
			if (M[i][j] > 0) {
				count++;
			}
		}
		return count;
	}

	// Work1-2c
	double idf(int[][] M, int j) {
		return Math.log((double)M.length / this.df(M, j));
	}
	// 課題1-2
	// text を特徴ベクトルに変換する。ベクトル空間を更新しない。
	Feature translate(String text) {
		// 1. BoWを生成
		BoW bow = BoW.create(text);
		// 2. 特徴ベクトルを生成
		ArrayList<BoW> bowList = new ArrayList<>();
		bowList.add(bow);
		Space space = new Space(bowList);

		Feature feature = new Feature(space.W[0]);

		return feature;
		}

	}