package controller;

import java.util.concurrent.Semaphore;

public class CrossingController extends Thread{
	
	Semaphore mutex;
	private int idCarro;
	
	public CrossingController(int idCarro, Semaphore mutex) {
		this.idCarro = idCarro;
		this.mutex = mutex;
	}
	
	public void run() {
		try {
			mutex.acquire();
			cruzamento();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			mutex.release();
		}
	}
	
	public void cruzamento() {
		//Escolhe o sentido do cruzamento
		String[] vetorSentido = {"Norte.", "Sul.", "Leste.", "Oeste."};
		int sentido = (int)(Math.random() * 4);
		
		//Faz cruzamento
		System.out.println("Carro " + (idCarro + 1) + " está fazendo o cruzamento sentido " + vetorSentido[sentido]);
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

}
