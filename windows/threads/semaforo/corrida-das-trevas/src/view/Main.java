package view;

import java.util.concurrent.Semaphore;
import controller.CorridaController;

public class Main {

	public static void main(String[] args) {
		
		String nomeCavaleito[] = {"Cavaleiro da Morte", "Cavaleiro da Guerra", "Cavaleiro da Pestilência", "Cavaleira da Fome"};
		Semaphore mutex1 = new Semaphore(1);
		Semaphore mutex2 = new Semaphore(1);
		Semaphore mutex3 = new Semaphore(1);
		for(int i = 0; i < 4; i++) {
			CorridaController corrida = new CorridaController(nomeCavaleito[i], mutex1, mutex2, mutex3);
			corrida.start();
		}
	}

}
