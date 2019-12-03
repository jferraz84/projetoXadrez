package secao16Xadrez;

import java.util.ArrayList;
import java.util.List;

import secao16TabuleiroJogo.Peca;
import secao16TabuleiroJogo.Posicao;
import secao16TabuleiroJogo.Tabuleiro;
import secao16Xadrez.pecas.Rei;
import secao16Xadrez.pecas.Torre;

public class PartidaDeXadrez {
	
	private int turno;
	private Color jogadorAtual;
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecasDoTabuleiro = new ArrayList<>();
	private List<Peca> capturaDePecas = new ArrayList<>();
	

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Color.BRANCO; 
		configInicial();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Color getJogadorAtual() {
		return jogadorAtual;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);

			}
		}
		return mat;
	}

	public boolean[][] podemMover(PosicaoXadrez posicaoOrigem){
		Posicao	posicao = posicaoOrigem.toPosicao();
		validatePosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentoPossiveis();
	}

	public PecaXadrez desempMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validatePosicaoOrigem(origem);
		validatePosicaoDestino(origem, destino);
		Peca capturaDePeca = fazerMover(origem, destino);
		nextTurno();
		return (PecaXadrez) capturaDePeca;
	}

	private void validatePosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.temPeca(posicao)) {
			throw new XadrezException("Nao existe peca na posicao de origem!!");
		}
		if (jogadorAtual !=  ((PecaXadrez)tabuleiro.peca(posicao)).getColor()) {
			throw new XadrezException("A peca escolhida nao e sua !!");
		}
		if (!tabuleiro.peca(posicao).algumMovimPossivel()) {
			throw new XadrezException("Nao existe movimentos para peca escolhida !");
		}
	}

	private void validatePosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).podeMover(destino)) {
			throw new XadrezException("A peca escolhida nao pode ser movida para possicao de destino!");
		}
	}

	private Peca fazerMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		Peca capturaDePeca = tabuleiro.removePeca(destino);
		tabuleiro.colocarPeca(p, destino);
		
		if (capturaDePeca != null) {
			pecasDoTabuleiro.remove(capturaDePeca);
			capturaDePecas.add(capturaDePeca);
			
		}
		return capturaDePeca;
	}

	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasDoTabuleiro.add(peca);
	}

	private void configInicial() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Color.BRANCO));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Color.BRANCO));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Color.BRANCO));
		colocarNovaPeca('e', 2, new Torre(tabuleiro, Color.BRANCO));
		colocarNovaPeca('e', 1, new Torre(tabuleiro, Color.BRANCO));
		colocarNovaPeca('d', 1, new Rei(tabuleiro, Color.BRANCO));

		colocarNovaPeca('c', 7, new Torre(tabuleiro, Color.PRETO));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Color.PRETO));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Color.PRETO));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Color.PRETO));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Color.PRETO));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Color.PRETO));
	}
	
	private void nextTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
	}
	
}
