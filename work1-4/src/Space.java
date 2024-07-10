import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

// Work1-2c
class Space {
	 // 索引語の初期値
	 ArrayList<String> indexTerms = new ArrayList<>();
	 
	 // Work1-2c
	 int[][] M; // 単語文章行列 M[i][j] = 文書 d_i 中における語 t_j の頻度
	 double[][] W; // 重み行列 W[i][j] = 文書 d_i 中における語 t_j の重み
	 
	 ArrayList<Feature> features = new ArrayList<>();
	 
	 ArrayList<BoW> bows = new ArrayList<>(); 

	// Work1-2c
	 Space(ArrayList<BoW> bows) {
		 
		 this.bows = bows; 
		 
	     // 1. 索引語の決定
	     this.indexTerms = this.makeIndexTerms(bows);
	     
	     // 2. 単語文書行列を初期化 
	     this.M = this.makeTermDocumentMatrix(bows);
	     
	     // 3. 語の重みを計算
	     this.W = this.makeWeightMatrix(this.M);
	     
	     this.features = this.createFeatures(this.W);
	 }

	// Work1-2c, Work1-2d
	ArrayList<String> makeIndexTerms(ArrayList<BoW> bows) {
		HashSet<String> terms = new HashSet<String>();
		for(BoW bow : bows) {
			terms.addAll(bow.termCount.keySet());
		}
		return new ArrayList<>(terms);
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
		  BoW bow = BoW.create(text);
		  double[] vector = new double[this.indexTerms.size()];
		  for (String term : bow.termCount.keySet()) {
		      int index = this.indexTerms.indexOf(term);
		      if (index != -1) {
		          vector[index] = bow.termCount.get(term) * idf(this.M, index);
		      }
		  }
		  return new Feature(vector); // 特徴ベクトル
		}

	ArrayList<Feature> createFeatures(double[][] W) {
	    ArrayList<Feature> features = new ArrayList<>();
	    for (int i = 0; i < W.length; i++) {
	        features.add(new Feature(W[i]));
	    }
	    return features;
	}
	
}