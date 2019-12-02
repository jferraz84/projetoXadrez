package secao16ProjetoXadrez;

import java.util.InputMismatchException;
import java.util.Scanner;

import secao16Xadrez.PartidaDeXadrez;
import secao16Xadrez.PecaXadrez;
import secao16Xadrez.PosicaoXadrez;
import secao16Xadrez.XadrezException;

public class JogoXadrez {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidadeXadrez = new PartidaDeXadrez();
		
		while (true) {
			try {
				UI.limparTela();
				UI.printTabuleiro(partidadeXadrez.getPecas());			
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.leiaPosicaoXadrez(sc);
				
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.leiaPosicaoXadrez(sc);
				
				PecaXadrez  capturaDePeca = partidadeXadrez.desempMovimentoXadrez(origem, destino);
				
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
