package controller;

import java.util.concurrent.Semaphore;

public class OvercookedController extends Thread{

	private int idPrato;
	private String prato;
	private int tempo;
	private Semaphore semaforo;
	private Semaphore mutex;
	
	//Construtor
	public OvercookedController(int idPrato, Semaphore semaforo, Semaphore mutex) {
		this.idPrato = idPrato;
		this.semaforo = semaforo;
		this.mutex = mutex;
	}
	
	public void run() {
		calculoId();
		//Semáforo que controla o cozimento de 5 pratos (threads) juntos
		try {
			semaforo.acquire();
			cozimento();
		} catch (InterruptedException e) {
			System.err.println(e);
		} finally {
			semaforo.release();
			//Semáforo que controla a entrega dos pratos, sendo um por vez
			try {
				mutex.acquire();
				entrega();
			} catch (InterruptedException e) {
				System.err.println(e);
			} finally {
				mutex.release();
			}
		}
	}
	
	//Método para ver se o ID é ímpar ou par
	private String calculoId() {
		if(idPrato % 2 == 0) {
			prato = "Sopa de Cebolas";
		} else {
			prato = "Lasanha a Bolonhesa";
		}
		return prato;
	}
	
	//Método para  calcular o tempo de cozimento de cada prato
	private void cozimento() {
		switch(prato) {
		case "Sopa de Cebolas":
			tempo = (int)((Math.random() * 301) + 500);
			preparo();
			break;
		case "Lasanha a Bolonhesa":
			tempo = (int)((Math.random() * 601) + 600);
			preparo();
			break;
		}
	}
	
	//Método para fazer o preparo dos pratos e mostrar seu progresso
	private void preparo() {
		double div = 0.1;
		System.out.println("A " + prato + "#" + idPrato + " começou a ser preparada.");
		try {
			double percentual = (tempo * div);
			int percentualAux = 10;
			int tempoSleep = tempo / 10;
			while(percentual <= tempo) {
				sleep(tempoSleep);
				System.out.println("A " + prato + "#" + idPrato + " está " + percentualAux + "% cozido.");
				percentualAux += 10;
				div += 0.1;
				percentual = tempo * div;
			}
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}
	
	//Método para efetuar a entrega dos pratos
	private void entrega() {
		System.out.println(prato + "#" + idPrato + " Entregando...");
		try {
			sleep(500);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		System.out.println(prato + "#" + idPrato + " entregue!");
	}

}
