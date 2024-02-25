package controller;

public class TempoVetorController {
	
	//Método Construtor:
	public TempoVetorController() {
		super();
	}
	
	//Método para preencher o vetor:
	public void tempoGasto(int tamanho) {
		
		int[] vetor = new int[tamanho];
		double tempoInicial = System.nanoTime();
		for(int i = 0; i < tamanho; i++) {
			vetor[i] = 0;
		}
		double tempoFinal = System.nanoTime();
		double tempoTotal = tempoFinal - tempoInicial;
		//tempoTotal nS = 10^-9 s
		tempoTotal = tempoTotal / Math.pow(10, 9);
		System.out.println("O tempo gasto para preencher o vetor de " + tamanho 
				+ " posições foi de: " + tempoTotal + " segundos.");
		
	}

}
