package secao16ProjetoXadrez;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import secao16Xadrez.PartidaDeXadrez;
import secao16Xadrez.PecaXadrez;
import secao16Xadrez.PosicaoXadrez;
import secao16Xadrez.XadrezException;

public class JogoXadrez {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidadeXadrez = new PartidaDeXadrez();
		List<PecaXadrez> capturada = new ArrayList<>();
		
		while (true) {
			try {
				UI.limparTela();
				UI.printPartida(partidadeXadrez, capturada);			
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.leiaPosicaoXadrez(sc);
				
				boolean [][] podemMover = partidadeXadrez.podemMover(origem);
				UI.limparTela();
				UI.printTabuleiro(partidadeXadrez.getPecas(), podemMover);
				
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.leiaPosicaoXadrez(sc);
				
				PecaXadrez  capturaDePeca = partidadeXadrez.desempMovimentoXadrez(origem, destino);
				
				if (capturaDePeca != null) {
					capturada.add(capturaDePeca);
				}
				
			} catch (XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
				
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
				
			}
		}
		
	}
}
