package view;

import controller.FcfsController;

import java.util.concurrent.Semaphore;

public class Main {

	/* 1) Considerando o esquema de escalonamento não preemptivo chamado FCFS, faça uma simulação 
	 * de execução de processos, em Java com Threads e Semáforos, considerando que 20 processos 
	 * foram carregados na memória, cada um deles pode durar de 4 a 120 segundos para rodar */
	public static void main(String[] args) {
		
		Semaphore mutex = new Semaphore(1);
		
		for(int id = 0; id < 20; id++) {
			FcfsController fcfs = new FcfsController(id, mutex);
			fcfs.start();
		}
	}

}
