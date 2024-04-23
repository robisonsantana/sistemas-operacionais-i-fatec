package view;

import controller.SjfController;

import java.util.concurrent.Semaphore;

public class Main {

	/* 2) Considerando o esquema de escalonamento não preemptivo chamado SJF, faça 
	 * uma simulação de execução de processos, em Java com Threads e Semáforos, 
	 * considerando que 20 processos foram carregados na memória, cada um deles 
	 * pode durar de 4 a 120 segundos para rodar */
	public static void main(String[] args) {
		
		Semaphore mutex = new Semaphore(1);
		for(int i = 0; i < 20; i++) {
			SjfController sjf = new SjfController(mutex);
			sjf.start();
		}
		

	}

}
