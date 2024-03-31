package view;

import java.util.concurrent.Semaphore;

import controller.TrainingController;

public class TrainingMain {

	/* 4) Você foi contratado para automatizar um treino de Fórmula 1. As regras estabelecidas pela 
	 * direção da provas são simples: “No máximo 5 carros das 7 escuderias (Cada escuderia tem 2 carros 
	 * diferentes, portanto, 14 carros no total) presentes podem entrar na pista simultaneamente, mas 
	 * apenas um carro de cada equipe. O segundo carro deve ficar à espera, caso um companheiro de equipe 
	 * já esteja na pista. Cada piloto deve dar 3 voltas na pista. O tempo de cada volta deverá ser exibido 
	 * e a volta mais rápida de cada piloto deve ser armazenada para, ao final, exibir o grid de largada, 
	 * ordenado do menor tempo para o maior.” */
	public static void main(String[] args) {

		Semaphore semaforo = new Semaphore(5);
		Semaphore equipe1 = new Semaphore(1);
		Semaphore equipe2 = new Semaphore(1);
		Semaphore equipe3 = new Semaphore(1);
		Semaphore equipe4 = new Semaphore(1);
		Semaphore equipe5 = new Semaphore(1);
		Semaphore equipe6 = new Semaphore(1);
		Semaphore equipe7 = new Semaphore(1);
		
		int[] escuderia = {3, 6, 5, 10, 7, 14, 11, 22, 13, 26, 17, 34, 19, 38};
		
		for(int item : escuderia) {
			TrainingController training = new TrainingController(item, equipe1, equipe2, equipe3, equipe4, equipe5, equipe6, equipe7, semaforo);
			training.start();
		}
		
	}

}
