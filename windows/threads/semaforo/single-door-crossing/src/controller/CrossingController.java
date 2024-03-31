package controller;

import java.util.concurrent.Semaphore;

public class CrossingController extends Thread{
	
	Semaphore mutex;
	private String nome;
	
	public CrossingController(String nome, Semaphore mutex) {
		this.nome = nome;
		this.mutex = mutex;
	}
	
	public void run() {
		corredor();
		try {
			mutex.acquire();
			porta();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			mutex.release();
		}
	}
	
	//Corredor de 200 metros
	private void corredor() {
		int percurso = 0;
		while(percurso < 200) {
			int velocidade = (int)((Math.random() * 3) + 4);
			percurso += velocidade;
			try {
				sleep(250);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			System.out.println(nome + " andou " + percurso + " metros do corredor.");
		}
	}
	
	//Cruzamento da porta
	private void porta() {
		System.out.println("-> " + nome + " está abrindo a porta. <-");
		int tempo = (int)((Math.random() * 1001) + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("-> " + nome + " cruzou a porta. <-");
	}

}
