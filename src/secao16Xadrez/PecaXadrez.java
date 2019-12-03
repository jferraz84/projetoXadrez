package secao16Xadrez;

import secao16TabuleiroJogo.Peca;
import secao16TabuleiroJogo.Posicao;
import secao16TabuleiroJogo.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Color color;

	public PecaXadrez(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro);
		this.color = color;
	}
	
	protected boolean existePecaOponente(Posicao posicao) {
		PecaXadrez p =  (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getColor() != color;
	}

	public Color getColor() {
		return color;
	}
	

}
