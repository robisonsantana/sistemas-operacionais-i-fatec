package controller;

import java.util.concurrent.Semaphore;

public class CorridaController extends Thread{

	private String cavaleiro;
	private int velocidade;
	private Semaphore mutex1;
	private Semaphore mutex2;
	private Semaphore mutex3;
	static boolean tocha = true;
	static boolean pedra = true;
	static int portaCorreta = (int)(Math.random() * 4);;
	static boolean[] portas = {true, true, true, true};
	static int portaEscolhida;
	
	public CorridaController(String cavaleiro, Semaphore mutex1, Semaphore mutex2, Semaphore mutex3) {
		this.cavaleiro = cavaleiro;
		this.mutex1 = mutex1;
		this.mutex2 = mutex2;
		this.mutex3 = mutex3;
	}
	
	@Override
	public void run() {
		velocidade = (int)(Math.random() * 3) + 2;
		corrida();
		portas();
	}
	
	//Simulador da corrida:
	private void corrida() {
		int percurso = 0;
		
		while(percurso < 2000) {
			percurso += velocidade;
			System.out.println(cavaleiro + " percorreu " + percurso + "m.");
			
			//Tocha
			try {
				mutex1.acquire();
				if(percurso >= 500 && tocha) {
					System.out.println("-> " + cavaleiro + " pegou a TOCHA e sua velocidade aumentou em 2m a cada 50ms. <-");
					velocidade += 2;
					tocha = false;
				}
			} catch (InterruptedException e1) {
				System.err.println(e1.getMessage());
			} finally {
				mutex1.release();
			}
			
			
			//Pedra Brilhante
			try {
				mutex2.acquire();
				if(percurso >= 1500 && pedra) {
					System.out.println("->> " + cavaleiro + " pegou a PEDRA BRILHANTE e sua velocidade aumentou em 2m a cada 50ms. <<-");
					velocidade += 2;
					pedra = false;
				}
			} catch (InterruptedException e2) {
				System.err.println(e2.getMessage());
			} finally {
				mutex2.release();
			}
			
			try {
				sleep(50);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		
	}
	
	//Somulador das portas
	private void portas() {
		
		portaEscolhida = (int)((Math.random() * 4) + 1);
		
		try {
			mutex3.acquire();
			while(portas[portaEscolhida - 1] == false) {
				portaEscolhida = (int)((Math.random() * 4) + 1);
			}
			
			portas[portaEscolhida - 1] = false;
			
			
		} catch (InterruptedException e3) {
			System.err.println(e3.getMessage());
		} finally {
			mutex3.release();
		}
		
		if(portaCorreta == portaEscolhida) {
			System.out.println("->>> " + cavaleiro + " escolheu a porta " + portaEscolhida + " e SOBREVIVEU!!! <<<-");
		} else {
			System.out.println("->>> " + cavaleiro + " escolheu a porta " + portaEscolhida + " e MORREU!!! <<<-");
		}
	}

}
