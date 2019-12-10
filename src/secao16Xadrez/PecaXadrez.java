package secao16Xadrez;

import secao16TabuleiroJogo.Peca;
import secao16TabuleiroJogo.Posicao;
import secao16TabuleiroJogo.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Color color;
	private int moverContagem;

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
	
	public int getMoverContagem() {
		return moverContagem;
	}
	
	public void aumentContagMovimento() {
		moverContagem ++;
	}
	
	public void diminuiContagMovimento() {
		moverContagem --;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.fromPosicao(posicao);
		
	}
}
