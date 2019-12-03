package secao16ProjetoXadrez;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import secao16Xadrez.Color;
import secao16Xadrez.PartidaDeXadrez;
import secao16Xadrez.PecaXadrez;
import secao16Xadrez.PosicaoXadrez;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
		public static void limparTela() {
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}

	public static PosicaoXadrez leiaPosicaoXadrez(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(coluna, linha);

		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro lendo posicao de Xadrez, "
					+ "Valores validos sao de A1 ate H8");
		}

	}
	
	public static void printPartida(PartidaDeXadrez partidaDeXadrez, List<PecaXadrez> capturada) {
		printTabuleiro(partidaDeXadrez.getPecas());
		System.out.println();
		printCapturaPecas(capturada);
		System.out.println();
		System.out.println("Turno: " + partidaDeXadrez.getTurno());
		System.out.println("Esperando o jogador jogar: " + partidaDeXadrez.getJogadorAtual());
		
	}

	public static void printTabuleiro(PecaXadrez[][] pecas) {
		System.out.println("------------------");
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + "| ");
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("------------------");
		System.out.println("   a b c d e f g h");
	}
	
	// Colorindo os movimentos possiveis 
	
	public static void printTabuleiro(PecaXadrez[][] pecas, boolean[][] podemMover) {
		System.out.println("------------------");
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + "| ");
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], podemMover[i][j]);
			}
			System.out.println();
		}
		System.out.println("------------------");
		System.out.println("   a b c d e f g h");
	}

	private static void printPeca(PecaXadrez peca, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peca.getColor() == Color.BRANCO) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	private static void printCapturaPecas(List<PecaXadrez> capturada) {
		List<PecaXadrez> branca = capturada.stream().filter(x -> x.getColor() == Color.BRANCO).collect(Collectors.toList());
		List<PecaXadrez> preto = capturada.stream().filter(x -> x.getColor() == Color.PRETO).collect(Collectors.toList());
		
		System.out.println("Captura de pecas: ");
		System.out.print("Branca: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(branca.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Preta:  ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(preto.toArray()));
		System.out.print(ANSI_RESET);
		
		
	}
}
