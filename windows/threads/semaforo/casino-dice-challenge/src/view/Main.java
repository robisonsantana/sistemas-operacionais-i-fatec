package view;

import model.Jogador;
import controller.Cassino;
import voltaic.guy.Fila;

public class Main {

	/* Um cassino resolveu promover uma competição na sua mesa de dados, como abaixo:
	 * - 10 competidores jogarão 2 dados com faces de 1 a 6 (Resultados aleatórios)
	 * - A soma dos dois dados deve dar 7 ou 11
	 * - Cada vez que o competidor tirar 1 dos dois valores, ele faz 1 ponto
	 * - O 1º competidor que fizer 5 pontos ganha 5000,00, o 2º ganha 4000,00, o 3º 3000,00
	 * - Os outros competidores não ganham nada. */
	public static void main(String[] args) {
		
		Fila<Jogador> filaJogadores = new Fila<>();
		
		Cassino c = new Cassino(filaJogadores);
		c.start();
	}

}
