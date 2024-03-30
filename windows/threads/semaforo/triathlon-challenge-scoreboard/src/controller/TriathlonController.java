package controller;

import java.util.concurrent.Semaphore;

public class TriathlonController extends Thread {
	
	private Semaphore semaforo;
	private Semaphore mutex;
	private static int[] vetorPontos = new int[25];
	private static int[] vetorId = new int[25];
	private static int[] vetorColocacao = new int[25];
	private static int espera = 0;
	private static int pontosChegada = 25;
	private static int ordemChegada = 1;
	private int idAtleta;
	private int pontosDisparos = 0;
	
	public TriathlonController(int idAtleta, Semaphore semaforo, Semaphore mutex) {
		this.idAtleta = idAtleta;
		this.semaforo = semaforo;
		this.mutex = mutex;
	}
	
	public void run() {
		corrida();
		try {
			semaforo.acquire();
			tiroAoAlvo();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
		}
		ciclismo();
		try {
			mutex.acquire();
			chegada();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			mutex.release();
		}
		placar();
	}
	
	//3Km de corrida onde os atletas correm entre 20 e 25 m / 30 ms
	private void corrida() {
		int percurso = 0;
		while(percurso <= 3000) {
			int velocidade = (int)((Math.random() * 6) + 20);
			percurso += velocidade;
			System.out.println("Atleta número " + (idAtleta + 1) + " correu " + percurso + "m.");
			try {
				sleep(30);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		System.out.println("-> Atleta número " + (idAtleta + 1) + " concluiu a corrida. <-");
	}
	
	//3 tiros ao alvo com pontuação de 0 a 10
	private void tiroAoAlvo() {
		for(int disparos = 1; disparos < 4; disparos++) {
			int velocidade = (int)((Math.random() * 2501) + 500);
			try {
				sleep(velocidade);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			int pontos = (int)(Math.random() * 11);
			System.out.println("Atleta número " + (idAtleta + 1) + " efetuou o " + disparos + "º disparo e marcou " + pontos + " pontos.");
			pontosDisparos += pontos;
		}
		System.out.println("->> Atleta número " + (idAtleta + 1) + " concluiu os disparos e marcou um total de " + pontosDisparos + " pontos. <<-");
	}
	
	//5 km de ciclismo onde os atletas pedalam entre 30 e 40 m/ 40 ms
	private void ciclismo() {
		int percurso = 0;
		while(percurso <= 5000) {
			int velocidade = (int)((Math.random() * 11) + 30);
			percurso += velocidade;
			System.out.println("Atleta número " + (idAtleta + 1) + " pedalou " + percurso + "m.");
			try {
				sleep(40);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		System.out.println("->>> Atleta número " + (idAtleta + 1) + " concluiu o percurso de bicicleta. <<<-");
	}
	
	//Chegada, onde é calculada e armazenada a pontuação total acumulada por cada atleta
	private void chegada() {
		int pontucaoTotal = (pontosChegada * 10) + pontosDisparos;
		System.out.println("->>>> Atleta número " + (idAtleta + 1) + " chegou em " + ordemChegada + "º lugar e marcou um total de " + pontucaoTotal + " pontos.");
		ordemChegada++;
		pontosChegada--;
		vetorPontos[idAtleta] = pontucaoTotal;
	}
	
	/* Placar, onde os atletas que finalizaram esperam pelos que ainda faltam, 
	 * as colocações são organizadas e divulgadas */
	private void placar() {
		espera++;
		while(espera < 25) {
			
		}
		
		for(int i = 0; i < 25; i++) {
			vetorId[i] = i + 1;
		}
		
		for(int i = 0; i < 25; i++) {
			vetorColocacao[i] = vetorPontos[i];
		}
		
		ScoreboerdController sc = new ScoreboerdController();
		sc.quickSort(vetorColocacao, 0, vetorColocacao.length - 1, vetorId);
		
	
		for(int i = 0; i < 25; i++) {
			System.out.println((i + 1) + "º LUGAR: atleta número " + vetorId[i] + " com um total de " + vetorColocacao[i] + " pontos.");
		}
	}
	
}
