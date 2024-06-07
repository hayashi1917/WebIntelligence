import java.util.List;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;

public class Main{
	public static void main(String[] args) {
		Tokenizer tokenizer = new Tokenizer();
		String text = "吾輩は猫である。";
		List<Token> tokens = tokenizer.tokenize(text);
		for(Token token: tokens) {
			System.out.print(token.getSurface()+" ");
		}
		System.out.println();
	}
}
