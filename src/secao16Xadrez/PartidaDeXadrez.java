package secao16Xadrez;

import secao16TabuleiroJogo.Peca;
import secao16TabuleiroJogo.Posicao;
import secao16TabuleiroJogo.Tabuleiro;
import secao16Xadrez.pecas.Rei;
import secao16Xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		configInicial();
	}
	
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
				
			}
		}
		return mat;
	}
	
	public PecaXadrez desempMovimentoXadrez (PosicaoXadrez  posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validatePosicaoOrigem(origem);
		Peca capturaDePeca = fazerMover(origem, destino);
		return (PecaXadrez)capturaDePeca;
	}
	
	private void validatePosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.temPeca(posicao)) {
			throw new XadrezException("Nao existe peca na posicao de origem!!");
			
		}
	}
	
	private Peca fazerMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		Peca capturaDePeca = tabuleiro.removePeca(destino);
		tabuleiro.colocarPeca(p, destino);  
		return capturaDePeca;
		
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca){
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	
	private void configInicial() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Color.BRANCO));
        colocarNovaPeca('c', 2, new Torre(tabuleiro, Color.BRANCO));
        colocarNovaPeca('d', 2, new Torre(tabuleiro, Color.BRANCO));
        colocarNovaPeca('e', 2, new Torre(tabuleiro, Color.BRANCO));
        colocarNovaPeca('e', 1, new Torre(tabuleiro, Color.BRANCO));
        colocarNovaPeca('d', 1, new Rei  (tabuleiro, Color.BRANCO));

        colocarNovaPeca('c', 7, new Torre(tabuleiro, Color.PRETO));
        colocarNovaPeca('c', 8, new Torre(tabuleiro, Color.PRETO));
        colocarNovaPeca('d', 7, new Torre(tabuleiro, Color.PRETO));
        colocarNovaPeca('e', 7, new Torre(tabuleiro, Color.PRETO));
        colocarNovaPeca('e', 8, new Torre(tabuleiro, Color.PRETO));
        colocarNovaPeca('d', 8, new Rei  (tabuleiro, Color.PRETO));
	}
}
