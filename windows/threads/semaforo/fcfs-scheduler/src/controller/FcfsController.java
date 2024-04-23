package controller;

import java.util.concurrent.Semaphore;

public class FcfsController extends Thread {
	
	private Semaphore mutex;
	private int idProcesso;
	
	public FcfsController(int idProcesso, Semaphore mutex) {
		this.idProcesso = idProcesso;
		this.mutex = mutex;
	}

	@Override
	public void run() {
		try {
			mutex.acquire();
			processamento();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			mutex.release();
		}
	}
	
	private void processamento() {
		int tempo = (int)((Math.random() * 117000) + 4000);
		System.out.println("Rodando processo #" + (idProcesso + 1) + ". Tempo estimado: " + (tempo / 1000) + "s.");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	

}
