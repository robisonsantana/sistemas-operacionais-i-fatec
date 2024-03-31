package controller;

import java.util.concurrent.Semaphore;

public class CrossingMain {
	
	/* 1) Fazer uma aplicação, console, que gerencie a figura abaixo: Para tal, usar uma variável 
	 * sentido, que será alterado pela Thread que controla cada carro com a movimentação do carro. 
	 * Quando a Thread tiver a possibilidade de ser executada, ela deve imprimir em console o 
	 * sentido que o carro está passando. Só pode passar um carro por vez no cruzamento. */
	public static void main(String[] args) {
		
		Semaphore mutex = new Semaphore(1);
		
		for(int idCarro = 0; idCarro < 10; idCarro++) {
			CrossingController crossing = new CrossingController(idCarro, mutex);
			crossing.start();
		}
	}

}
