import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;

// Work1-2c
class Space {
	// 索引語の初期値
	ArrayList<String> indexTerms = new ArrayList<>() {{
		add("りんご");
		add("みかん");
		add("いちご");
	}};
	
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
		int[][] A = new int[bows.size()][this.indexTerms.size()];

		return A;
	}

	// Work1-2c
	// 重み行列の生成
	double[][] makeWeightMatrix(int[][] M) {
		double[][] B = new double[M.length][this.indexTerms.size()];

		return B;
	}
	
	// Work1-2c
	double tfidf(int[][] M, int i, int j) {

	}

	// Work1-2c
	double tf(int[][] M, int i, int j) {

	}
	
	// Work1-2c
	double df(int[][] M, int j) {

	}

	// Work1-2c
	double idf(int[][] M, int j) {

	}
	
	// 課題1-2
	// text を特徴ベクトルに変換する。ベクトル空間を更新しない。
	Feature translate(String text) {

	}
}
