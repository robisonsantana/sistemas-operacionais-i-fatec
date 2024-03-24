package controller;

import java.util.concurrent.Semaphore;

public class SimuladorController extends Thread{

	private int resto;
	private int idThread;
	private int tempo;
	private Semaphore mutex;
	
	//Construtor
	public SimuladorController(int idThread, Semaphore mutex) {
		this.idThread = idThread;
		this.mutex = mutex;
	}
	
	public void run() {
		calculoResto();
		try {
			mutex.acquire();
			processamento();
		} catch (InterruptedException e) {
			System.err.println(e);
		} finally {
			mutex.release();
		}
	}
	
	//Método para calcular o resto da divisão
	private int calculoResto() {
		resto = idThread % 3;
		return resto;
	}
	
	//Método para silular o tempo das transações com o banco de dados
	private void processamento() {
		switch(resto) {
		case 1:
			tempo = (int)((Math.random() * 801) + 200);
			try {
				System.out.println("#" + idThread + " está calculando.");
				sleep(tempo);
				System.out.println("#" + idThread + " está fazendo a transação com o banco de dados");
				sleep(1000);
				System.out.println("#" + idThread + " finalizou a transação");
			} catch (InterruptedException e) {
				System.err.println(e);
			}
			break;
		case 2:
			tempo = (int)((Math.random() * 1001) + 500);
			try {
				System.out.println("#" + idThread + " está calculando.");
				sleep(tempo);
				System.out.println("#" + idThread + " está fazendo a transação com o banco de dados");
				sleep(1500);
				System.out.println("#" + idThread + " finalizou a transação");
			} catch (InterruptedException e) {
				System.err.println(e);
			}
			break;
		case 0:
			tempo = (int)((Math.random() * 1001) + 1000);
			try {
				System.out.println("#" + idThread + " está calculando.");
				sleep(tempo);
				System.out.println("#" + idThread + " está fazendo a transação com o banco de dados");
				sleep(1500);
				System.out.println("#" + idThread + " finalizou a transação");
			} catch (InterruptedException e) {
				System.err.println(e);
			}
			break;
		}
	}

}
