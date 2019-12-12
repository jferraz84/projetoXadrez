package secao16Xadrez.pecas;

import secao16TabuleiroJogo.Posicao;
import secao16TabuleiroJogo.Tabuleiro;
import secao16Xadrez.Color;
import secao16Xadrez.PartidaDeXadrez;
import secao16Xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	private PartidaDeXadrez partidaDeXadrez;

	public Rei(Tabuleiro tabuleiro, Color color, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, color);
		this.partidaDeXadrez = partidaDeXadrez;

	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean podesMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p == null || p.getColor() != getColor();

	}

	private boolean testeTorrePraRoque(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p != null && p instanceof Torre && p.getColor() == getColor() && p.getMoverContagem() == 0;

	}

	@Override
	public boolean[][] movimentoPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// ACIMA
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// ABAIXO
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// ESQUERDA
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// DIREITA
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// NOROESTE
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// NORDESTE
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// SUDOESTE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// SUDESTE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podesMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// # Movimento especial de ROQUE
		if (getMoverContagem() == 0 && !partidaDeXadrez.getCheck()) {
			// #Movimento especial ROQUE PEQUENO
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if (testeTorrePraRoque(posT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}

			}

			// #Movimento especial ROQUE GRANDE
			Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if (testeTorrePraRoque(posT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null
						&& getTabuleiro().peca(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}

			}

		}

		return mat;
	}

}
