package secao16Xadrez;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import secao16TabuleiroJogo.Peca;
import secao16TabuleiroJogo.Posicao;
import secao16TabuleiroJogo.Tabuleiro;
import secao16Xadrez.pecas.Rei;
import secao16Xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Color jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;

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
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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

	public boolean[][] podemMover(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosicao();
		validatePosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentoPossiveis();
	}

	public PecaXadrez desempMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validatePosicaoOrigem(origem);
		validatePosicaoDestino(origem, destino);
		Peca capturaDePeca = fazerMover(origem, destino);
		
		if (testeCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, capturaDePeca);
			throw new XadrezException(" Voce nao pode se colocar em check !");
		}
		
		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		
		if (testeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		} else {
		nextTurno();
		}
		
		return (PecaXadrez) capturaDePeca;
		
	}

	private void validatePosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.temPeca(posicao)) {
			throw new XadrezException("Nao existe peca na posicao de origem!!");
		}
		if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getColor()) {
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

	private void desfazerMovimento(Posicao origem, Posicao destino, Peca capturaDePeca) {
		Peca p = tabuleiro.removePeca(destino);
		tabuleiro.colocarPeca(p, origem);
		
		if (capturaDePeca != null) {
			tabuleiro.colocarPeca(capturaDePeca, destino);
			capturaDePecas.remove(capturaDePeca);
			pecasDoTabuleiro.add(capturaDePeca);

		}
	}
	
	private void nextTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
	}


	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasDoTabuleiro.add(peca);
	}
	
	private Color oponente(Color color) {
		return (color == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
	}
	
	private PecaXadrez Rei(Color color) {
		List<Peca> list = pecasDoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList());
		
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Nao existe o Rei " + color + " no tabuleiro !");
	}
	
	private boolean testeCheck(Color color) {
		Posicao ReiPosicao = Rei(color).getPosicaoXadrez().toPosicao();
		List<Peca> oponentePecas = pecasDoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getColor() == oponente(color)).collect(Collectors.toList());
		
		for (Peca p : oponentePecas) {
			boolean[][] mat = p.movimentoPossiveis();
			
			if (mat[ReiPosicao.getLinha()][ReiPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testeCheckMate(Color color) {
		if (!testeCheck(color)) {
			return false;
		}
		List<Peca> list = pecasDoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getColor() == color).collect(Collectors.toList());
		
		for (Peca p : list) {
			boolean[][] mat = p.movimentoPossiveis();
			
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(i, j);
						Peca captudaDePeca = fazerMover(origem, destino);
						boolean testeCheck = testeCheck(color);
						desfazerMovimento(origem, destino, captudaDePeca);
						
						if (!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void configInicial() {
		/*colocarNovaPeca('c', 1, new Torre(tabuleiro, Color.BRANCO));
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
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Color.PRETO));*/
		
		colocarNovaPeca('h', 7, new Torre(tabuleiro, Color.BRANCO));
		colocarNovaPeca('d', 1, new Torre(tabuleiro, Color.BRANCO));
		colocarNovaPeca('e', 1, new Rei  (tabuleiro, Color.BRANCO));
		
		colocarNovaPeca('b', 8, new Torre(tabuleiro, Color.PRETO));
		colocarNovaPeca('a', 8, new Rei  (tabuleiro, Color.PRETO));
		
	}

	
}
