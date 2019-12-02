package secao16Xadrez;

import secao16TabuleiroJogo.Peca;
import secao16TabuleiroJogo.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Color color;

	public PecaXadrez(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

}
