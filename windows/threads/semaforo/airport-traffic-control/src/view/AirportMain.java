package view;

import java.util.concurrent.Semaphore;

import controller.AirportController;

public class AirportMain {

	/* 2. Um aeroporto tem 2 pistas (norte e sul) e, em cada pista, apenas um avião pode fazer a decolagem. 
	 * O procedimento de decolagem tem 4 fases ( taxiar, decolagem e afastamento da área). A fase de manobra 
	 * pode durar de 300 a 700 milissegundos. A fase de taxiar, de 500 a 1000 milissegundos. A fase de decolagem, 
	 * de 600 a 800 milissegundos. A fase de afastamento, de 300 a 800 milissegundos. O aeroporto reúne, por ciclo, 
	 * 12 aeronaves que podem decolar pela pista norte ou pela pista sul (decisão aleatória) mas, apenas 2 aviões 
	 * podem circular pela área de decolagem ao mesmo tempo. Fazer uma aplicação em Java que resolva o problema. */
	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(2);
		Semaphore mutex = new Semaphore(1);
		
		for(int aeronaveId = 0; aeronaveId < 12; aeronaveId++) {
			AirportController airport = new AirportController(aeronaveId, semaforo, mutex);
			airport.start();
		}

	}

}
