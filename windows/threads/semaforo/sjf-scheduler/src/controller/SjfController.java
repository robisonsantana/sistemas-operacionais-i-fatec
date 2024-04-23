package controller;

import java.util.concurrent.Semaphore;

public class SjfController extends Thread {
	
	private Semaphore mutex;
	private static int[] vetorTempo = new int[20];
	private static int[] vetorIndice = new int[20];
	private static int controller = 0;
	
	public SjfController(Semaphore mutex) {
		this.mutex = mutex;
	}

	@Override
	public void run() {
		tempo();
		try {
			mutex.acquire();
			processamento();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			mutex.release();
		}
	}
	
	//Define o tempo de cada processo
	private void tempo() {
		SjfSorting sorting = new SjfSorting();
		
		for(int i = 0; i < 20; i++) {
			vetorTempo[i] = (int)((Math.random() * 16001) + 4000);
			vetorIndice[i] = i + 1;
		}
		controller++;
		while(controller < 20) {
			//Espera todos os processos definirem o tempo
		}
		sorting.quickSort(vetorTempo, 0, vetorTempo.length - 1, vetorIndice);
	}
	
	//Simula o processamento
	private void processamento() {
		for(int i = 0; i < 20; i++) {
			int tempo = (vetorTempo[i] / 1000);
			System.out.println("Rodando processo #" + vetorIndice[i] + ". Tempo estimado: " + tempo + "s.");
			try {
				sleep(vetorTempo[i]);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			
		}
	}

}
