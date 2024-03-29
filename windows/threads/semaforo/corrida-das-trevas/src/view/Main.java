package view;

import java.util.concurrent.Semaphore;
import controller.CorridaController;

public class Main {

	/* 1. Simular em Java:
	 * 4 cavaleiros caminham por um corredor, simultaneamente, de 2 a 4 m por 50 ms. O corredor é escuro, 
	 * tem 2 km e em 500 m, há uma única tocha. O cavaleiro que pegar a tocha, aumenta sua velocidade, 
	 * somando mais 2 m por 50 ms ao valor que já fazia. Em 1,5 km, existe uma pedra brilhante. O cavaleiro 
	 * que pegar a pedra, aumenta sua velocidade, somando mais 2 m por 50 ms ao valor que já fazia. Ao final 
	 * dos 2 km, abrem uma porta randômica km, os cavaleiros se separam com 4 portas e, um por vez pega uma 
	 * porta aleatória (que não pode repetir) e entra nela. Apenas uma porta leva à saída. As outras 3 tem 
	 * monstros que os devoram. */
	public static void main(String[] args) {
		
		String nomeCavaleito[] = {"Cavaleiro da Morte", "Cavaleiro da Guerra", "Cavaleiro da Pestil�ncia", "Cavaleira da Fome"};
		Semaphore mutex1 = new Semaphore(1);
		Semaphore mutex2 = new Semaphore(1);
		Semaphore mutex3 = new Semaphore(1);
		for(int i = 0; i < 4; i++) {
			CorridaController corrida = new CorridaController(nomeCavaleito[i], mutex1, mutex2, mutex3);
			corrida.start();
		}
	}

}
