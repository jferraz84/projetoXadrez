package secao16Xadrez.pecas;

import secao16TabuleiroJogo.Tabuleiro;
import secao16Xadrez.Color;
import secao16Xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
		
	}

	@Override
	public String toString() {
		return "T";
	}
  
	
}
