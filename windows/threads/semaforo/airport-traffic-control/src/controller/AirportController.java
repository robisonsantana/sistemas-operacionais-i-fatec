package controller;

import java.util.concurrent.Semaphore;

public class AirportController extends Thread{
	
	private static boolean[] pistas = {true, true};
	private int pistaEscolhida;
	private Semaphore semaforo;
	private Semaphore mutex;
	private String pistaEscolhidaNome;
	private int aeronaveId;
	
	public AirportController(int aeronaveId, Semaphore semaforo, Semaphore mutex) {
		this.aeronaveId = aeronaveId;
		this.semaforo = semaforo;
		this.mutex = mutex;
	}
	
	@Override
	public void run() {
		pistas();
	}
	
	//Controle de pistas e decolagens
	private void pistas() {
		
		//Controle da escolha das pistas
		try {
			mutex.acquire();
			pistaEscolhida = (int)(Math.random() * 2);
			while(!pistas[pistaEscolhida]) {
				pistaEscolhida = (int)(Math.random() * 2);
			}
			pistas[pistaEscolhida] = false;
			
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			mutex.release();
		}
		
		//Controle do uso das pistas e decolagem
		try {
			semaforo.acquire();
			if(pistaEscolhida == 0) {
				pistaEscolhidaNome = "A";
			} else {
				pistaEscolhidaNome = "B";
			}
			decolagem();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
		}
	}
	
	//Passo a passo da decolagem
	private void decolagem() {

		//Fase 1: Manobra
		System.out.println("A aeronave " + aeronaveId + " está em processo de manobra na pista " + pistaEscolhidaNome + ".");
		int tempo = (int)((Math.random() * 401) + 300);
		tempo(tempo);
		
		//Fase 2: Taxiar
		System.out.println("A aeronave " + aeronaveId + " está em processo de movimentação no solo na pista " + pistaEscolhidaNome + ".");
		tempo = (int)((Math.random() * 501) + 500);
		tempo(tempo);
		
		//Fase 3: Decolagem
		System.out.println("A aeronave " + aeronaveId + " está em processo de decolagem na pista " + pistaEscolhidaNome + ".");
		tempo = (int)((Math.random() * 201) + 600);
		tempo(tempo);
		
		//Fase 4: Afastamento
		System.out.println("A aeronave " + aeronaveId + " está em processo de afastamento na pista " + pistaEscolhidaNome + ".");
		tempo = (int)((Math.random() * 500) + 300);
		tempo(tempo);
		
		System.out.println("PROCESSO DE DECOLAGEM COMPLETO, PISTA " + pistaEscolhidaNome + " LIVRE.");
		pistas[pistaEscolhida] = true;
		
	}
	
	//Execução do tempo de cada fase
	private void tempo(int tempo) {
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

}
