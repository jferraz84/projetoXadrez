package secao16Xadrez.pecas;

import secao16TabuleiroJogo.Posicao;
import secao16TabuleiroJogo.Tabuleiro;
import secao16Xadrez.Color;
import secao16Xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

	public Cavalo (Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);

	}

	@Override
	public String toString() {
		return "C";
	}

	private boolean podesMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p == null || p.getColor() != getColor(); 

	}

	@Override
	public boolean[][] movimentoPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		p.setValores(posicao.getLinha() -1, posicao.getColuna() -2);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
	
		p.setValores(posicao.getLinha() -2, posicao.getColuna() -1);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() -2, posicao.getColuna() +1);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() -1, posicao.getColuna() +2);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() +1, posicao.getColuna() +2);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() +2, posicao.getColuna() +1);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() +2, posicao.getColuna() -1);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() +1, posicao.getColuna() -2);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}

