package controller;

public class ThreadVetor extends Thread{

	private int valorNumerico;
	private int[] vetor;
	
	//Construtor
	public ThreadVetor(int valorNumerico, int[] vetor) {
		this.valorNumerico = valorNumerico;
		this.vetor = vetor;
	}
	
	@Override
	public void run() {
		percorrerVetor(valorNumerico, vetor);
	}
	
	/* Percorrer o vetor e calcular quanto tempo leva para ele ser 
	 * percorrido com o for e com o foreach */
	private void percorrerVetor(int valorNumerico, int[] vetor) {
		
		if(valorNumerico % 2 == 0) {
			double tempoInicial = System.nanoTime();
			for(int i = 0; i < vetor.length; i++) {
				System.out.print(vetor[i] + " | ");
			}
			double tempoFinal = System.nanoTime();
			double tempoTotal = (tempoFinal - tempoInicial) / Math.pow(10, 9);
			System.out.println("\nTempo gasto para percorrer vetor com for: " + tempoTotal + "s");
		} else if(valorNumerico % 2 != 0) {
			double tempoInicial = System.nanoTime();
			for(int valor : vetor) {
				System.out.print(valor + " | ");
			}
			double tempoFinal = System.nanoTime();
			double tempoTotal = (tempoFinal - tempoInicial) / Math.pow(10, 9);
			System.out.println("\nTempo gasto para percorrer vetor com foreach: " + tempoTotal + "s");
		}
	}

}
