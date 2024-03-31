package controller;

import java.util.concurrent.Semaphore;

public class TrainingController extends Thread{

	private Semaphore semaforo;
	private Semaphore equipe1;
	private Semaphore equipe2;
	private Semaphore equipe3;
	private Semaphore equipe4;
	private Semaphore equipe5;
	private Semaphore equipe6;
	private Semaphore equipe7;
	private static int espera = 0;
	private static double[] melhoresTempos = {3, 6, 5, 10, 7, 14, 11, 22, 13, 26, 17, 34, 19, 38};
	private static int[] idVetor = {3, 6, 5, 10, 7, 14, 11, 22, 13, 26, 17, 34, 19, 38};
	private double tempo1;
	private double tempo2;
	private double tempo3;
	private double melhorTempo;
	private int equipe;
	private int idCarro;

	public TrainingController(int idCarro, Semaphore equipe1, Semaphore equipe2, Semaphore equipe3, Semaphore equipe4, 
							  Semaphore equipe5, Semaphore equipe6, Semaphore equipe7, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.equipe3 = equipe3;
		this.equipe4 = equipe4;
		this.equipe5 = equipe5;
		this.equipe6 = equipe6;
		this.equipe7 = equipe7;
		this.semaforo = semaforo;
	}
	
	public void run() {
		escuderias();
		finalizacao();
	}
	
	private void escuderias() {
		
		//Verificando de que equipe pertence cada carro e controlando a entrada de carros na pista 
		if(idCarro % 3 == 0) {
			try {
				equipe = 1;
				equipe1.acquire();
				pistaController();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				equipe1.release();
			}
		} else if(idCarro % 5 == 0) {
			try {
				equipe = 2;
				equipe2.acquire();
				pistaController();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				equipe2.release();
			}
		} else if (idCarro % 7 == 0) {
			try {
				equipe = 3;
				equipe3.acquire();
				pistaController();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				equipe3.release();
			}
		} else if (idCarro % 11 == 0) {
			try {
				equipe = 4;
				equipe4.acquire();
				pistaController();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				equipe4.release();
			}
		} else if (idCarro % 13 == 0) {
			try {
				equipe = 5;
				equipe5.acquire();
				pistaController();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				equipe5.release();
			}
		} else if (idCarro % 17 == 0) {
			try {
				equipe = 6;
				equipe6.acquire();
				pistaController();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				equipe6.release();
			}
		} else {
			try {
				equipe = 7;
				equipe7.acquire();
				pistaController();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				equipe7.release();
			}
		}
	}
	
	//Controle de entrada na pista
	private void pistaController() {
		try {
			semaforo.acquire();
			pista();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
		}
	}
	
	
	//Corrida e controle do tempo das voltas
	private void pista() {
		
		int volta = 1;
		tempo1 = voltas(volta);
		System.out.println("Motorista do carro " + idCarro + " concluiu sua 1º volta e o seu tempo foi: " + tempo1 + "s.");
		
		volta = 2;
		tempo2 = voltas(volta);
		System.out.println("Motorista do carro " + idCarro + " concluiu sua 2º volta e o seu tempo foi: " + tempo2 + "s.");
		
		volta = 3;
		tempo3 = voltas(volta);
		System.out.println("Motorista do carro " + idCarro + " concluiu sua 3º volta e o seu tempo foi: " + tempo3 + "s.");
		
		melhorTempo = tempo1;
		if(melhorTempo < tempo2) {
			melhorTempo = tempo2;
		} else if(melhorTempo < tempo3) {
			melhorTempo = tempo3;
		}
		
		int tamanho = melhoresTempos.length;
		for(int i = 0; i < tamanho; i++) {
			if(idCarro == melhoresTempos[i]) {
				melhoresTempos[i] = melhorTempo;
			}
		}
		
	}
	
	//Controla a corrida e retorna o tempo de cada volta
	public double voltas(int volta) {
		
		int circuito = 0;
		double tempoInicial = System.nanoTime();
		while(circuito < 1000) {
			int velocidade = (int)((Math.random() * 301) + 200);
			circuito += velocidade;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			System.out.println(volta + "º Volta: motorista do carro " + idCarro + " equipe " + equipe + " percorreu " + circuito + " do circuito.");
		}
		double tempoFinal = System.nanoTime();
		double tempoTotal = (tempoFinal - tempoInicial) / (1 * Math.pow(10, 9));
		return tempoTotal;
	}
	
	//Organiza o grid de partida e retorna a colocação de cada motorista
	private void finalizacao() {
		
		espera++;
		while(espera < 14) {
			//Motoristas aguardam os outros terminarem seus percursos 
		}
		
		RaceResultController result = new RaceResultController();
		result.quickSort(melhoresTempos, 0, melhoresTempos.length - 1, idVetor);
		
		System.out.println("\nGRID DE LARGADA: ");
		int tamanho = melhoresTempos.length;
		for(int i = 0; i < tamanho; i++) {
			System.out.println((i + 1) + "º LUGAR: Motorista do carro " + idVetor[i] + " com um tempo de " + melhoresTempos[i] + "s.");
		}
		
		System.out.println("\nEquipes: \n"
				+ "1: carros 3 e 6 \n"
				+ "2: carros 5 e 10 \n"
				+ "3: carros 7 e 14 \n"
				+ "4: carros 11 e 22 \n"
				+ "5: carros 13 e 26 \n"
				+ "6: carros 17 e 34 \n"
				+ "7: carros 19 e 38 ");
		
	}

}
