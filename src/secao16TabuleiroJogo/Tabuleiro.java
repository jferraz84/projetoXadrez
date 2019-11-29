package secao16TabuleiroJogo;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroException("� necess�rio que acha 1 linha e 1 coluna !!");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
		
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public Peca peca(int linha, int coluna) {
		if (!posicaoExiste(linha, coluna)) {
			throw new TabuleiroException("Posi��o inexistente no Tabuleiro !!");
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca (Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posi��o nula !");
			
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocarPeca(Peca peca, Posicao posicao) {
		if (temPeca(posicao)) {
			throw new TabuleiroException("J� existe uma pe�a na posi��o " + posicao);
	}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	private boolean posicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean temPeca (Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posi��o nula !");
			
		}
		return peca(posicao) != null;
	}
}
