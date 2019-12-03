package secao16Xadrez.pecas;

import secao16TabuleiroJogo.Posicao;
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

	@Override
	public boolean[][] movimentoPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// ACIMA
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());

		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getLinha()] = true;
		}

		// para ESQUERDA

		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);

		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
			;
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getColuna()][p.getColuna()] = true;
		}

		
		// para DIREITA
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);

		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getColuna()][p.getColuna()] = true;
		}
		
		// PARA BAIXO
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());

		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getLinha()] = true;
		}

		return mat;
	}

}
