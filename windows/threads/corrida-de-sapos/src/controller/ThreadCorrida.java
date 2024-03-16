package controller;

public class ThreadCorrida extends Thread {

	private int puloMax;
	private int distanciaMax;
	private String nome;
	
	//Construtor
	public ThreadCorrida(int puloMax, int distanciaMax, String nome) {
		this.puloMax = puloMax;
		this.distanciaMax = distanciaMax;
		this.nome = nome;
	}
	
	@Override
	public void run() {
		corrida(puloMax, distanciaMax, nome);
	}
	
	private void corrida(int puloMax, int distanciaMax, String nome) {
		
		int distanciaPercorrida = 0;
		
		//Looping para ser percorrido até que o sapo conclua o percurso:
		while(distanciaPercorrida < distanciaMax) {
			
			//Número aleatório gerado para o salto, respeitando o polo máximo:
			int salto = (int)(Math.random() * (puloMax + 1));
			distanciaPercorrida += salto;
			
			//Mostrando quantos metros o sapo pulou:
			System.out.println("Sapo " + nome + " pulou " + salto + " metros...");
			
			try {
				//Pausa da thread por um segundo para deixar a corrida mais realista:
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Print mostrando que o sapo chegou:
		System.out.println("\n-> Sapo " + nome + " chegou!!! <-\n");
	}

}
